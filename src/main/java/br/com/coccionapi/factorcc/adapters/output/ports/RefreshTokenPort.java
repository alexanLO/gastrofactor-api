package br.com.coccionapi.factorcc.adapters.output.ports;

import java.util.Optional;

import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;

public interface RefreshTokenPort {

    RefreshTokenCommand save(RefreshTokenCommand refreshToken);

    Optional<RefreshTokenCommand> findByToken(String token);

}
