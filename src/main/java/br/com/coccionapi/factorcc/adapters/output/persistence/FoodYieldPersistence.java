package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.util.Optional;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.FoodYieldEntity;
import br.com.coccionapi.factorcc.adapters.output.persistence.mappers.CalculatorMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.FoodYieldJpaRepository;
import br.com.coccionapi.factorcc.domain.model.FoodYieldVO;
import br.com.coccionapi.factorcc.port.output.FoodYieldPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class FoodYieldPersistence implements FoodYieldPort {

    private final CalculatorMapper calculatorMapper;
    private final FoodYieldJpaRepository foodYieldJpaRepository;

    @Override
    public Optional<FoodYieldVO> getByName(String foodName) {
        log.debug("Consultando alimento pelo nome: {}", foodName);
        FoodYieldEntity foodYieldEntity = foodYieldJpaRepository.findByFoodNameIgnoreCase(foodName);
        
        Optional<FoodYieldVO> result = Optional.ofNullable(foodYieldEntity)
                .map(entity -> {
                    log.debug("Alimento encontrado: id={}, nome={}, fator_correcao={}, fator_cocção={}", 
                        entity.getId(), entity.getFoodName(), entity.getCorrectionFactor(), entity.getCoccionFactor());
                    return calculatorMapper.fromEntityToModel(entity);
                });
        
        if (result.isEmpty()) {
            log.warn("Alimento não encontrado para o nome: {}", foodName);
        }
        
        return result;
    }

}
