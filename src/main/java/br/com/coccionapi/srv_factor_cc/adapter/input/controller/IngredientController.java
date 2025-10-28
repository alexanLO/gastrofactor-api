package br.com.coccionapi.srv_factor_cc.adapter.input.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coccionapi.srv_factor_cc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.CookingFactorResponse;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.CorrectionFactorResponse;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.IngredientResponse;
import br.com.coccionapi.srv_factor_cc.adapter.input.mapper.IngredientMapper;
import br.com.coccionapi.srv_factor_cc.exceptions.BusinessException;
import br.com.coccionapi.srv_factor_cc.port.input.CookingFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.CorrectionFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Ingredientes", description = "Gerenciamento de igredientes e calculo do fator de cocção e correção.")
@ApiResponses(value = {
                @ApiResponse(responseCode = "400", description = "Solicitação Inválida", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "401", description = "Não Autorizado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "403", description = "Proibido", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "422", description = "Não é possivel processar os dados recebidos", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "429", description = "Muitas solicitações realizadas", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "500", description = "Erro Interno do Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class))),
                @ApiResponse(responseCode = "503", description = "Serviço Indiponivel", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponse.class)))
})
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ingredient")
public class IngredientController {

        private final IngredientUseCase ingredientUseCase;
        private final CorrectionFactorUseCase correctionFactorUseCase;
        private final CookingFactorUseCase cookingFactorUseCase;

        private final IngredientMapper mapper;

        @Operation(summary = "Registrando um novo ingrediente", method = "POST")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Registra um ingrediente com sucesso", content = @Content(schema = @Schema(implementation = BusinessException.class)))
        })
        @PostMapping()
        public ResponseEntity<IngredientResponse> register(@RequestBody @Valid IngredientRequest request) {

                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(mapper.toDTOResponse(ingredientUseCase.register(mapper.toModelRequest(request))));
        }

        @Operation(summary = "Calculando fator de correção", method = "GET")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Fator de correção calculado com sucesso", content = @Content(schema = @Schema(implementation = CorrectionFactorResponse.class))),
                        @ApiResponse(responseCode = "422", description = "Peso de ingrediente bruto ou liquido não pode ser 0", content = @Content(schema = @Schema(implementation = BusinessException.class)))
        })
        @GetMapping("/{id}/correction-factor")
        public ResponseEntity<CorrectionFactorResponse> calculateCorrectionFactor(@PathVariable UUID id) {
                log.info("Buscando o calculo do fator de correção do id: {}", id);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(mapper.toCorrectionFactorResponse(
                                                correctionFactorUseCase.calculateCorrectionFactor(id)));
        }

        @Operation(summary = "Calculando o fator de cocção", method = "GET")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Fator de cocção calculado com sucesso", content = @Content(schema = @Schema(implementation = CookingFactorResponse.class))),
                        @ApiResponse(responseCode = "422", description = "Peso de ingrediente cru ou cozido não pode ser 0", content = @Content(schema = @Schema(implementation = BusinessException.class)))
        })
        @GetMapping("/{id}/cooking-factor")
        public ResponseEntity<CookingFactorResponse> calculateCookingFactor(@PathVariable UUID id) {
                log.info("Buscando o calculo do fator de cocção do id: {}", id);

                return ResponseEntity.status(HttpStatus.OK)
                                .body(mapper.toCookingFactorResponse(cookingFactorUseCase.calculateCookingFactor(id)));
        }

}
