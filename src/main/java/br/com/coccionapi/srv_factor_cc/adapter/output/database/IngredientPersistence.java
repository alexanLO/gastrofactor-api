package br.com.coccionapi.srv_factor_cc.adapter.output.database;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.coccionapi.srv_factor_cc.adapter.output.database.mapper.EntityMapper;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.repository.CorrectionFactorRepository;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.repository.IngredientRepository;
import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.port.output.ConsultIngredientPort;
import br.com.coccionapi.srv_factor_cc.port.output.CorrectionFactorPort;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IngredientPersistence implements IngredientPort, ConsultIngredientPort, CorrectionFactorPort {

    private static final String NOT_FOUND = "Ingrediente não encontrado.";

    private final IngredientRepository ingredientRepository;
    private final CorrectionFactorRepository cFactorRepository;

    private final EntityMapper mapper;

    @Override
    public Ingredient register(Ingredient modelRequest) {
        return mapper.toModelIngredient(ingredientRepository.save(mapper.toSaveEntity(modelRequest)));
    }

    @Override
    public Ingredient searchingById(UUID id) {
        // TODO Criar um NotFoundException personalizado.
        return mapper.toModelIngredient(ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(NOT_FOUND)));
    }

    // * Fator de Correção Repository/Criteria */
    @Override
    public CorrectionFactor register(CorrectionFactor request) {
        return mapper.toModelFC(cFactorRepository.save(mapper.toSaveFCEntity(request)));
    }
}
