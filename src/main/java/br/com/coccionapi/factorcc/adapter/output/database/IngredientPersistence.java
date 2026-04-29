package br.com.coccionapi.factorcc.adapter.output.database;

import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapter.output.database.mapper.EntityMapper;
import br.com.coccionapi.factorcc.adapter.output.database.repository.CookingFactorRepository;
import br.com.coccionapi.factorcc.adapter.output.database.repository.CorrectionFactorRepository;
import br.com.coccionapi.factorcc.adapter.output.database.repository.IngredientRepository;
import br.com.coccionapi.factorcc.domain.model.CookingFactor;
import br.com.coccionapi.factorcc.domain.model.CorrectionFactor;
import br.com.coccionapi.factorcc.domain.model.Ingredient;
import br.com.coccionapi.factorcc.exceptions.NotFoundException;
import br.com.coccionapi.factorcc.port.output.ConsultIngredientPort;
import br.com.coccionapi.factorcc.port.output.CookingFactorPort;
import br.com.coccionapi.factorcc.port.output.CorrectionFactorPort;
import br.com.coccionapi.factorcc.port.output.IngredientPort;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class IngredientPersistence implements IngredientPort, ConsultIngredientPort, CorrectionFactorPort, CookingFactorPort {

    private static final String NOT_FOUND = "Ingrediente não encontrado.";

    private final IngredientRepository ingredientRepository;
    private final CorrectionFactorRepository cFactorRepository;
    private final CookingFactorRepository cookingFactorRepository;

    private final EntityMapper mapper;

    @Override
    public Ingredient registerIngredient(Ingredient modelRequest) {
        return mapper.toModelIngredient(ingredientRepository.save(mapper.toSaveEntity(modelRequest)));
    }

    @Override
    public Ingredient searchingById(UUID id) {
        return mapper.toModelIngredient(ingredientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND)));
    }

    // * Fator de Correção Repository/Criteria */
    @Override
    public CorrectionFactor registerCorrectionFactor(CorrectionFactor request) {
        return mapper.toModelCorrectionFactor(cFactorRepository.save(mapper.toSaveCorrectionFactorEntity(request)));
    }
    
    // * Fator de cocção' Repository/Criteria */
    @Override
    public CookingFactor registerCookingFactor(CookingFactor request) {
        return mapper.toModelCookingFactor(cookingFactorRepository.save(mapper.toSaveCookingFactorEntity(request)));
    }
}
