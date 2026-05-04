package br.com.coccionapi.factorcc.domain.model;

import java.math.BigDecimal;

public record FoodYieldVO(String foodName, BigDecimal correctionFactor, BigDecimal coccionFactor) {
} 
