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
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.CorrectionFactorResponse;
import br.com.coccionapi.srv_factor_cc.adapter.input.dto.responses.IngredientResponse;
import br.com.coccionapi.srv_factor_cc.adapter.input.mapper.IngredientMapper;
import br.com.coccionapi.srv_factor_cc.port.input.CorrectionFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ingredient")
public class IngredientController {

    private final IngredientUseCase ingredientUseCase;
    private final CorrectionFactorUseCase correctionFactorUseCase;

    private final IngredientMapper mapper;

    @PostMapping()
    public ResponseEntity<IngredientResponse> register(@RequestBody @Valid IngredientRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDTOResponse(ingredientUseCase.register(mapper.toModelRequest(request))));
    }

    @GetMapping("/{id}/fator-correcao")
    public ResponseEntity<CorrectionFactorResponse> calculateCorrectionFactor(@PathVariable UUID id) {
        log.info("Buscando o calcular do fator de correção do id: {}", id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(mapper.toCorrectionFactorResponse(correctionFactorUseCase.calculateCorrectionFactor(id)));
    }

}
