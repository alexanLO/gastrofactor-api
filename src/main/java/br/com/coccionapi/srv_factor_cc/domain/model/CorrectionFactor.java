package br.com.coccionapi.srv_factor_cc.domain.model;

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
public class CorrectionFactor {

    private UUID id;

    private UUID ingredientId;

    private BigDecimal grossWeight;

    private BigDecimal netWeight;

    private BigDecimal correctionFactor;

    private LocalDateTime calculatedAt;
}