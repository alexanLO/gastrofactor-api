package br.com.coccionapi.factorcc.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private UUID id;

    private String name;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private BigDecimal rawWeight;

    private BigDecimal cookedWeight;

    private LocalDateTime registrationDate;
}
