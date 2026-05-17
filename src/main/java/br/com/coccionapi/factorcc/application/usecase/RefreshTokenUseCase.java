package br.com.coccionapi.factorcc.application.usecase;

import br.com.coccionapi.factorcc.domain.model.AuthVO;

public interface RefreshTokenUseCase {

    AuthVO refresh(String refreshToken);

}
