package br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RefreshRequest {

    @NotNull(message = "Token é obrigatório")
    private String refreshToken;

}
