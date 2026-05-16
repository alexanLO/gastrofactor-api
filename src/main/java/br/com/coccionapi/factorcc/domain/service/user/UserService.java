package br.com.coccionapi.factorcc.domain.service.user;

import org.springframework.http.HttpStatus;

import br.com.coccionapi.factorcc.adapters.output.ports.PasswordEncoderPort;
import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.application.usecase.RegisterUserUseCase;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import br.com.coccionapi.factorcc.domain.enums.ProvidersEnum;
import br.com.coccionapi.factorcc.domain.model.AuthVO;
import br.com.coccionapi.factorcc.infrastructure.exceptions.BusinessException;
import br.com.coccionapi.factorcc.shared.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserService implements RegisterUserUseCase {

    
    private final PasswordEncoderPort passwordEncoderPort;
    private final UserPort userPort;
    private final JwtUtils jwtUtils;

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

        return new AuthVO(jwtUtils.generateToken(command));
    }
}
