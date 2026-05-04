package br.com.coccionapi.factorcc.infrastructure.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.coccionapi.factorcc.adapters.input.mappers.CalculatorMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.FoodYieldAdapter;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.FoodYieldJpaRepository;
import br.com.coccionapi.factorcc.application.usecase.calculator.CalculatorUseCaseImp;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CookedCalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.GrossCalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.NetCalculatorStrategy;
import br.com.coccionapi.factorcc.port.input.CalculatorUseCase;
import br.com.coccionapi.factorcc.port.output.FoodYieldPort;

@Configuration
public class BeanConfig {

    @Bean
    public GrossCalculatorStrategy grossCalculatorStrategy() {
        return new GrossCalculatorStrategy();
    }

    @Bean
    public NetCalculatorStrategy netCalculatorStrategy() {
        return new NetCalculatorStrategy();
    }

    @Bean
    public CookedCalculatorStrategy cookedCalculatorStrategy() {
        return new CookedCalculatorStrategy();
    }

    @Bean
    public FoodYieldPort foodYieldPort(FoodYieldJpaRepository foodYieldJpaRepository, CalculatorMapper calculatorMapper) {
        return new FoodYieldAdapter(calculatorMapper, foodYieldJpaRepository);
    }

    @Bean
    public CalculatorUseCase calculatorUseCase(FoodYieldPort foodYieldPort, List<CalculatorStrategy> strategies) {
        return new CalculatorUseCaseImp(foodYieldPort, strategies);
    }
}
