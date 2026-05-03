package br.com.coccionapi.factorcc.domain.service.calculator.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.coccionapi.factorcc.domain.command.CalculatorCommand;
import br.com.coccionapi.factorcc.domain.enums.TypeWeightEnum;
import br.com.coccionapi.factorcc.domain.model.CalculatorVO;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;
import br.com.coccionapi.factorcc.shared.utils.EnumUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NetCalculatorStrategy implements CalculatorStrategy {

  @Override
  public boolean supports(TypeWeightEnum typeWeight) {
    EnumUtils.validateNotNull(typeWeight, "Tipo de peso não pode ser nulo");
    return typeWeight == TypeWeightEnum.NET;
  }

  @Override
  public CalculatorVO calculate(CalculatorCommand command, FoodYieldVO foodYieldVO) {
    log.debug("Iniciando cálculo para peso liquido: alimento={}, peso_liquido={}", command.foodName(),
        command.foodWeight());

    BigDecimal net = command.foodWeight();
    BigDecimal gross = net.multiply(foodYieldVO.correctionFactor());
    BigDecimal cooked = net.divide(foodYieldVO.coccionFactor(), 3, RoundingMode.HALF_UP);

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
