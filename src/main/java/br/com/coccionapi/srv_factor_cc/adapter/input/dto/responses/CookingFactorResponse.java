package br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses;

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
public class CookingFactorResponse {
    
    private UUID id;

    private UUID ingredientId;

    private BigDecimal cookedWeight;

    private BigDecimal rawWeight;

    private BigDecimal cookingFactor;

    private LocalDateTime calculatedAt;
}
