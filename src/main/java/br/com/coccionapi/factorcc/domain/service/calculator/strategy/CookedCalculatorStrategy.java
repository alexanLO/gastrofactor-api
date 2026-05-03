package br.com.coccionapi.factorcc.domain.service.calculator.strategy;

import java.math.BigDecimal;

import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.enums.TypeWeightEnum;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookedCalculatorStrategy implements CalculatorStrategy {

    @Override
    public boolean supports(TypeWeightEnum typeWeight) {
        return typeWeight == TypeWeightEnum.COOKED;
    }

    @Override
    public CalculatorVO calculate(CalculatorCommand command, FoodYieldVO foodYieldVO) {
        log.debug("Iniciando cálculo para peso cozido: alimento={}, peso_cozido={}", command.foodName(),
                command.foodWeight());

        BigDecimal cooked = command.foodWeight();
        BigDecimal net = cooked.multiply(foodYieldVO.coccionFactor());
        BigDecimal gross = net.multiply(foodYieldVO.correctionFactor());

        log.debug("Cálculo intermediário: peso_bruto={}, peso_líquido={}, peso_cozido={}", gross, net, cooked);

        return enrichResponse(command.foodName(), gross, net, cooked);
    }

    private CalculatorVO enrichResponse(String foodName, BigDecimal gross, BigDecimal net, BigDecimal cooked) {
        CalculatorVO response = CalculatorVO.builder()
                .foodName(foodName)
                .grossWeight(gross)
                .netWeight(net)
                .cookedWeight(cooked)
                .build();
        log.debug("Resposta enriquecida criada para alimento: {}", foodName);
        return response;
    }
}
