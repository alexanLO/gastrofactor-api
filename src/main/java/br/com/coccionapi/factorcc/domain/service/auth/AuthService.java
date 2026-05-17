package br.com.coccionapi.factorcc.domain.service.auth;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;

import br.com.coccionapi.factorcc.adapters.output.ports.PasswordEncoderPort;
import br.com.coccionapi.factorcc.adapters.output.ports.RefreshTokenPort;
import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.application.usecase.LoginUserUseCase;
import br.com.coccionapi.factorcc.application.usecase.RefreshTokenUseCase;
import br.com.coccionapi.factorcc.application.usecase.RegisterUserUseCase;
import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import br.com.coccionapi.factorcc.domain.command.UserLoginCommand;
import br.com.coccionapi.factorcc.domain.enums.ProvidersEnum;
import br.com.coccionapi.factorcc.domain.model.AuthVO;
import br.com.coccionapi.factorcc.infrastructure.exceptions.BusinessException;
import br.com.coccionapi.factorcc.shared.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AuthService implements RegisterUserUseCase, LoginUserUseCase, RefreshTokenUseCase {

    private final UserPort userPort;
    private final JwtUtils jwtUtils;
    private final RefreshTokenPort refreshTokenPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public AuthVO register(UserCommand command) {
        log.info("Cadastrando usuário nome = {}, email = {} ", command.getName(), command.getEmail());

        userPort.findUserByEmail(command.getEmail())
                .ifPresent(user -> {
                    throw new BusinessException(
                            HttpStatus.CONFLICT.value(),
                            "Já existe um usuário com este email.");
                });

        command.setPassword(passwordEncoderPort.encode(command.getPassword()));
        command.setProvider(ProvidersEnum.LOCAL);
        command.setRole("USER");

        userPort.register(command);

        return new AuthVO(jwtUtils.generateToken(command), null);
    }

    @Override
    public AuthVO login(UserLoginCommand command) {
        log.info("Login de usuário email: {}", command.email());

        var user = userPort.findUserByEmail(command.email())
                .orElseThrow(() -> new BusinessException(HttpStatus.UNAUTHORIZED.value(), "Email ou senha invalida."));

        boolean passwordMatches = passwordEncoderPort.matches(command.password(), user.getPassword());

        if (!passwordMatches) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "Email ou senha inválidos");
        }

        String token = jwtUtils.generateToken(user);

        RefreshTokenCommand refreshTokenCommand = this.createRefreshToken(user);

        return new AuthVO(token, refreshTokenCommand.getToken().toString());
    }

    @Override
    public AuthVO refresh(String token) {

        RefreshTokenCommand refreshTokenCommand = this.validate(token);

        UserCommand userCommand = refreshTokenCommand.getUser();

        this.revoke(refreshTokenCommand);

        String newAccessToken = jwtUtils.generateToken(userCommand);

        RefreshTokenCommand newRefresh = this.createRefreshToken(userCommand);

        return new AuthVO(newAccessToken, newRefresh.getToken().toString());
    }

    // Metodos Auxiliares -------------

    public RefreshTokenCommand createRefreshToken(UserCommand user) {

        RefreshTokenCommand refreshToken = new RefreshTokenCommand(UUID.randomUUID().toString(),
                LocalDateTime.now().plusDays(7),
                false,
                user);

        return refreshTokenPort.save(refreshToken);

    }

    private RefreshTokenCommand validate(String token) {
        RefreshTokenCommand refreshToken = refreshTokenPort.findByToken(token)
                .orElseThrow(() -> new BusinessException(HttpStatus.UNAUTHORIZED.value(), "Refresh token inválido"));

        if (refreshToken.isRevoked()) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "Refresh token revogado");
        }

        if (refreshToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(), "Refresh token expirado");
        }

        return refreshToken;
    }

    public void revoke(RefreshTokenCommand token) {
        token.setRevoked(true);
        refreshTokenPort.save(token);
    }
}
