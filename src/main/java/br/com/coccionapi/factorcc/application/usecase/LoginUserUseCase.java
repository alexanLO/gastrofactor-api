package br.com.coccionapi.factorcc.application.usecase;

import br.com.coccionapi.factorcc.domain.command.UserLoginCommand;
import br.com.coccionapi.factorcc.domain.model.AuthVO;

public interface LoginUserUseCase {

    AuthVO login(UserLoginCommand loginRequest);

}
