package br.com.coccionapi.factorcc.adapters.input.controllers.calculator;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.request.CalculatorRequest;
import br.com.coccionapi.factorcc.adapters.input.controllers.calculator.dto.response.CalculatorResponse;
import br.com.coccionapi.factorcc.adapters.output.persistence.mappers.CalculatorMapper;
import br.com.coccionapi.factorcc.application.usecase.calculator.CalculatorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/calculadora")
public class CalculatorController implements CalculatorSwagger {

    private final CalculatorMapper mapper;
    private final CalculatorUseCase calculatorUseCase;

    @Override
    @PostMapping
    public ResponseEntity<CalculatorResponse> calculator(@Valid @RequestBody CalculatorRequest request) {
        log.info("Iniciando chamada da calculadora. Nome do alimento = {}, peso = {} e tipo de peso = {}",
                request.getFoodName(), request.getFoodWeight(), request.getTypeWeight());

        CalculatorResponse response = mapper
                .toResponseDTO(calculatorUseCase.calculator(mapper.toRequestModel(request)));

        log.info("Chamada da calculadora finalizada com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
