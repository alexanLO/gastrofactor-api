package br.com.coccionapi.factorcc.infrastructure.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.coccionapi.factorcc.adapters.mappers.CalculatorMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.FoodYieldPersistence;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.FoodYieldJpaRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.AuthPort;
import br.com.coccionapi.factorcc.adapters.output.ports.FoodYieldPort;
import br.com.coccionapi.factorcc.adapters.output.ports.PasswordEncoderPort;
import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.application.usecase.CalculatorUseCase;
import br.com.coccionapi.factorcc.application.usecase.LoginUserUseCase;
import br.com.coccionapi.factorcc.application.usecase.LogoutUseCase;
import br.com.coccionapi.factorcc.application.usecase.RegisterUserUseCase;
import br.com.coccionapi.factorcc.domain.service.auth.AuthService;
import br.com.coccionapi.factorcc.domain.service.calculator.CalculatorService;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.CookedCalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.GrossCalculatorStrategy;
import br.com.coccionapi.factorcc.domain.service.calculator.strategy.NetCalculatorStrategy;
import br.com.coccionapi.factorcc.shared.utils.JwtUtils;

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
    public FoodYieldPort foodYieldPort(FoodYieldJpaRepository foodYieldJpaRepository,
            CalculatorMapper calculatorMapper) {
        return new FoodYieldPersistence(calculatorMapper, foodYieldJpaRepository);
    }

    @Bean
    public CalculatorUseCase calculatorUseCase(FoodYieldPort foodYieldPort, List<CalculatorStrategy> strategies) {
        return new CalculatorService(foodYieldPort, strategies);
    }

    @Bean
    public RegisterUserUseCase registerUserUseCase(PasswordEncoderPort passwordEncoderPort,
            AuthPort authPort,
            UserPort userPort,
            JwtUtils jwtUtils) {
        return new AuthService(authPort, jwtUtils, userPort, passwordEncoderPort);
    }

    @Bean
    public LoginUserUseCase loginUserUseCase(PasswordEncoderPort passwordEncoderPort,
            AuthPort authPort,
            UserPort userPort,
            JwtUtils jwtUtils) {
        return new AuthService(authPort, jwtUtils, userPort, passwordEncoderPort);
    }

    @Bean
    public LogoutUseCase logoutUseCase(PasswordEncoderPort passwordEncoderPort,
            AuthPort authPort,
            UserPort userPort,
            JwtUtils jwtUtils) {
        return new AuthService(authPort, jwtUtils, userPort, passwordEncoderPort);
    }

    @Bean
    public JwtUtils jwtUtils(@Value("${jwt.secret}") String secret) {
        return new JwtUtils(secret);
    }
}
