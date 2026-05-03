package br.com.coccionapi.factorcc.domain.command;

import java.math.BigDecimal;

import br.com.coccionapi.factorcc.domain.enums.TypeWeightEnum;

public record CalculatorCommand(String foodName, BigDecimal foodWeight, TypeWeightEnum typeWeight) {
}
