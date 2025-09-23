package br.com.coccionapi.srv_factor_cc.adapter.output.database;

import org.springframework.stereotype.Component;

import br.com.coccionapi.srv_factor_cc.adapter.output.database.mapper.EntityMapper;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.repository.IngredientRepository;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IngredientPersistence implements IngredientPort{
    
    private final IngredientRepository ingredientRepository;

    private final EntityMapper mapper;

    @Override
    public Ingredient register(Ingredient modelRequest) {
       return mapper.ToModel(ingredientRepository.save(mapper.toSaveEntity(modelRequest)));
    }
    
}
