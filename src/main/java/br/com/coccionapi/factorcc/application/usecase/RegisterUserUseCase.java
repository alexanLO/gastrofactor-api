package br.com.coccionapi.factorcc.application.usecase;

import br.com.coccionapi.factorcc.domain.command.UserCommand;
import br.com.coccionapi.factorcc.domain.model.AuthVO;

public interface RegisterUserUseCase {

    AuthVO register(UserCommand command);

}
