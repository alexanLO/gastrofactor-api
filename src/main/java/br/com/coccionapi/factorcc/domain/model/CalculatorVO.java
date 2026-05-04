package br.com.coccionapi.factorcc.domain.model;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CalculatorVO(String foodName, BigDecimal grossWeight, BigDecimal netWeight, BigDecimal cookedWeight) {}