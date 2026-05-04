package br.com.coccionapi.factorcc.adapters.input.controllers.calculator;

import org.springframework.http.ResponseEntity;

import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.request.CalculatorRequest;
import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.response.CalculatorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

public interface CalculatorSwagger {

    @Operation(summary = "Calcula o fator de correção e cocção do alimento", description = "Realiza o cálculo do fator de correção e cocção com base nas informações do alimento e do tipo de peso informado", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculo realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado"),
            @ApiResponse(responseCode = "404", description = "Alimento não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<CalculatorResponse> calculator(@Valid @RequestBody CalculatorRequest request);
}