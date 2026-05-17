package br.com.coccionapi.factorcc.adapters.output.ports;

import java.util.Optional;

import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;
import br.com.coccionapi.factorcc.domain.command.UserCommand;

public interface AuthPort {

    void registerUser(UserCommand command);

    RefreshTokenCommand saveRefreshToken(RefreshTokenCommand refreshToken);

    Optional<RefreshTokenCommand> findByRefreshToken(String token);
}
