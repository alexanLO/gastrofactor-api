package br.com.coccionapi.factorcc.adapters.output.ports;

import java.util.Optional;

import br.com.coccionapi.factorcc.domain.command.UserCommand;

public interface UserPort {

    Optional<UserCommand> findUserByEmail(String email);
}
