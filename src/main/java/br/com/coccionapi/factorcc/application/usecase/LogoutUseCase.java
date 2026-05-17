package br.com.coccionapi.factorcc.application.usecase;

public interface LogoutUseCase {

    void logout(String accessToken, String refreshToken);

}
