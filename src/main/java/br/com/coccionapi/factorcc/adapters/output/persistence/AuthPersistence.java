package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapters.mappers.AuthMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.RefreshTokenRepository;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.UserRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.AuthPort;
import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthPersistence implements AuthPort {

    private final AuthMapper authMapper;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void registerUser(UserCommand command) {

        userRepository.save(authMapper.toEntity(command));
    }

    @Override
    public RefreshTokenCommand saveRefreshToken(RefreshTokenCommand refreshToken) {
        return authMapper
                .toRefreshTokenCommand(refreshTokenRepository.save(authMapper.toRefreshTokenEntity(refreshToken)));
    }

    @Override
    public Optional<RefreshTokenCommand> findByRefreshToken(String token) {
        return Optional.ofNullable(
                authMapper.toRefreshTokenCommand(refreshTokenRepository.findByToken(token)));
    }

}
