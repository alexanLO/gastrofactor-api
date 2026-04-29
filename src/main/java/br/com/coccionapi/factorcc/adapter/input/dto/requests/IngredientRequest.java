package br.com.coccionapi.factorcc.adapter.input.dto.requests;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {

    @NotBlank(message = "Campo Obrigatório")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "Campo Obrigatório")
    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal grossWeight;

    @NotNull(message = "Campo Obrigatório")
    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal netWeight;

    @NotNull(message = "Campo Obrigatório")
    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal rawWeight;

    @NotNull(message = "Campo Obrigatório")
    @DecimalMin(value = "0.1", inclusive = true)
    private BigDecimal cookedWeight;
}
