package br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request;

public record LogoutRequest(String accessToken, String refreshToken) {
}