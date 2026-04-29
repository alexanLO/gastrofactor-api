package br.com.coccionapi.srv_factor_cc.adapter.output.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.coccionapi.factorcc.adapter.output.database.IngredientPersistence;
import br.com.coccionapi.factorcc.adapter.output.database.mapper.EntityMapper;
import br.com.coccionapi.factorcc.adapter.output.database.repository.CookingFactorRepository;
import br.com.coccionapi.factorcc.adapter.output.database.repository.CorrectionFactorRepository;
import br.com.coccionapi.factorcc.adapter.output.database.repository.IngredientRepository;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as funcionalidades da camada de Persistência")
public class IngredientPersistenceTest extends IngredientMock {

    @Mock
    private IngredientRepository repositoryIngredient;

    @Mock
    private CorrectionFactorRepository correctionFactorRepository;

    @Mock
    private CookingFactorRepository cookingFactorRepository;

    @Mock
    private EntityMapper mapper;

    @InjectMocks
    private IngredientPersistence persistence;

    @Test
    @DisplayName("Deve salvar usuário no banco de dados")
    void mustRegisterIngredient() {

        var expected = createIngredientFaker();
        var result = createIngredientEntityFaker();

        when(repositoryIngredient.save(result)).thenReturn(result);
        when(mapper.toSaveEntity(expected)).thenReturn(result);

        persistence.registerIngredient(expected);

        verify(repositoryIngredient, times(1)).save(result);
        assertEquals(expected.getName(), result.getName());
    }

    @Test
    @DisplayName("Deve salvar o fator de correção no banco de dados")
    void mustRegisterCorrectionFactor() {

        var expected = createCorrectionFactorFaker();
        var result = createCorrectionFactorEntityFaker();

        when(correctionFactorRepository.save(result)).thenReturn(result);
        when(mapper.toSaveCorrectionFactorEntity(expected)).thenReturn(result);

        persistence.registerCorrectionFactor(expected);

        verify(correctionFactorRepository, times(1)).save(result);
        assertEquals(expected.getId(), result.getId());
    }

    @Test
    @DisplayName("Deve salvar o fator de cocção no banco de dados")
    void mustRegisterCookingFactor() {

        var expected = createCookingFactorFaker();
        var result = createCookingFactorEntityFaker();

        when(cookingFactorRepository.save(result)).thenReturn(result);
        when(mapper.toSaveCookingFactorEntity(expected)).thenReturn(result);

        persistence.registerCookingFactor(expected);

        verify(cookingFactorRepository, times(1)).save(result);
        assertEquals(expected.getId(), result.getId());
    }
}
