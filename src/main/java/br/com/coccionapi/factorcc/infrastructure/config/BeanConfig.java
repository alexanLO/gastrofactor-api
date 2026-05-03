package br.com.coccionapi.factorcc.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.coccionapi.factorcc.adapters.output.persistence.FoodYieldPersistence;
import br.com.coccionapi.factorcc.adapters.output.persistence.mappers.CalculatorMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.FoodYieldJpaRepository;
import br.com.coccionapi.factorcc.application.usecase.calculator.CalculatorUseCase;
import br.com.coccionapi.factorcc.application.usecase.calculator.CalculatorUseCaseImp;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.GrossCalculatorStrategy;
import br.com.coccionapi.factorcc.port.output.FoodYieldPort;

@Configuration
public class BeanConfig {

    @Bean
    public GrossCalculatorStrategy grossCalculatorStrategy() {
        return new GrossCalculatorStrategy();
    }

    @Bean
    public FoodYieldPort foodYieldPort(FoodYieldJpaRepository foodYieldJpaRepository, CalculatorMapper calculatorMapper) {
        return new FoodYieldPersistence(calculatorMapper, foodYieldJpaRepository);
    }

    @Bean
    public CalculatorUseCase calculatorUseCase(FoodYieldPort foodYieldPort, List<CalculatorStrategy> strategies) {
        return new CalculatorUseCaseImp(foodYieldPort, strategies);
    }
}
