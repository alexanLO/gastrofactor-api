package br.com.coccionapi.factorcc.adapters.input.api.calculator.dto.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorResponse {
    private String foodName;
    private BigDecimal grossWeight;
    private BigDecimal netWeight;
    private BigDecimal cookedWeight;
}
