package br.com.coccionapi.srv_factor_cc.exceptions.response;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Especifico para validacao de API
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonTypeName("negocio")
public class SubErrorBusiness implements ApiSubErrorResponse {

    private final String code;
    private final String message;
}
