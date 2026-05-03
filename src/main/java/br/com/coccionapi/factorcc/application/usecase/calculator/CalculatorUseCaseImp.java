package br.com.coccionapi.factorcc.application.usecase.calculator;

import java.util.List;

import org.springframework.http.HttpStatus;

import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CalculatorStrategy;
import br.com.coccionapi.factorcc.infrastructure.exceptions.BusinessException;
import br.com.coccionapi.factorcc.infrastructure.exceptions.NotFoundException;
import br.com.coccionapi.factorcc.port.output.FoodYieldPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CalculatorUseCaseImp implements CalculatorUseCase {

    private final FoodYieldPort foodYieldPort;
    private final List<CalculatorStrategy> caculatorStrategy;

    @Override
    public CalculatorVO calculator(CalculatorCommand command) {
        log.info(
                "Iniciando chamada na camada da service com os dados: nome do alimento = {}, peso do alimento = {} e tipo de peso = {}",
                command.foodName(), command.foodWeight(), command.typeWeight());

        FoodYieldVO foodYield = foodYieldPort.getByName(command.foodName())
                .orElseThrow(() -> new NotFoundException("Alimento não encontrado"));
        log.debug("Alimento encontrado: nome={}, fator_correcao={}, fator_cocção={}",
                foodYield.foodName(), foodYield.correctionFactor(), foodYield.cookingYield());

        CalculatorStrategy strategy = caculatorStrategy.stream()
                .filter(s -> s.supports(command.typeWeight()))
                .findFirst()
                .orElseThrow(() -> new BusinessException(HttpStatus.BAD_REQUEST.value(), "Tipo de peso não suportado"));
        log.debug("Estratégia selecionada para tipo de peso: {}", command.typeWeight());

        CalculatorVO result = strategy.calculate(command, foodYield);
        log.info("Cálculo finalizado: nome={}, peso_bruto={}, peso_líquido={}, peso_cozido={}",
                result.foodName(), result.grossWeight(), result.netWeight(), result.cookedWeight());

        return result;
    }
}
