package br.com.coccionapi.srv_factor_cc.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.coccionapi.factorcc.domain.model.CookingFactor;
import br.com.coccionapi.factorcc.domain.model.CorrectionFactor;
import br.com.coccionapi.factorcc.domain.model.Ingredient;
import br.com.coccionapi.factorcc.domain.service.IngredientService;
import br.com.coccionapi.factorcc.port.output.ConsultIngredientPort;
import br.com.coccionapi.factorcc.port.output.CookingFactorPort;
import br.com.coccionapi.factorcc.port.output.CorrectionFactorPort;
import br.com.coccionapi.factorcc.port.output.IngredientPort;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as regras de negócios da camada service")
public class IngredientServiceTest extends IngredientMock {

    @Mock
    private IngredientPort ingredientPort;

    @Mock
    private CorrectionFactorPort correctionFactorPort;

    @Mock
    private ConsultIngredientPort consultIngredientPort;

    @Mock
    private CookingFactorPort cookingFactorPort;

    @InjectMocks
    private IngredientService service;

    @Test
    @DisplayName("Deve registrar um novo ingrediente com sucesso")
    void mustRegisterIngredient() {

        var request = mock(Ingredient.class);

        when(ingredientPort.registerIngredient(any(Ingredient.class))).thenReturn(createIngredientFaker());

        service.register(request);

        verify(ingredientPort, times(1)).registerIngredient(request);
    }

    @Test
    @DisplayName("Deve registrar o calculo do fator de correção com sucesso")
    void mustCalculateCorrectionFactor() {

        var request = createCorrectionFactorFaker();

        when(consultIngredientPort.searchingById(ID)).thenReturn(createIngredientFaker());
        when(correctionFactorPort.registerCorrectionFactor(any(CorrectionFactor.class))).thenReturn(request);

        var response = service.calculateCorrectionFactor(ID);

        verify(correctionFactorPort, times(1)).registerCorrectionFactor(any(CorrectionFactor.class));
        assertEquals(request, response);
    }

    @Test
    @DisplayName("Deve registrar o calculo do fator de cocção com sucesso")
    void mustCalculateCookingFactor() {

        var request = createCookingFactorFaker();

        when(consultIngredientPort.searchingById(ID)).thenReturn(createIngredientFaker());
        when(cookingFactorPort.registerCookingFactor(any(CookingFactor.class))).thenReturn(request);

        var response = service.calculateCookingFactor(ID);

        verify(cookingFactorPort, times(1)).registerCookingFactor(any(CookingFactor.class));
        assertEquals(request, response);
    }
}
