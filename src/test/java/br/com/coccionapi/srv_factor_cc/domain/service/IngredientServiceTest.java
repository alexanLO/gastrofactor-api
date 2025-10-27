package br.com.coccionapi.srv_factor_cc.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;
import br.com.coccionapi.srv_factor_cc.port.output.ConsultIngredientPort;
import br.com.coccionapi.srv_factor_cc.port.output.CorrectionFactorPort;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as regras de negócios da camada service")
public class IngredientServiceTest extends IngredientMock {

    @Mock
    private IngredientPort ingredientPort;

    @Mock
    private CorrectionFactorPort correctionFactorPort;

    @Mock
    private ConsultIngredientPort consultIngredientPort;

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
        when(correctionFactorPort.registerCF(any(CorrectionFactor.class))).thenReturn(request);

        var response = service.calculateCorrectionFactor(ID);

        verify(correctionFactorPort, times(1)).registerCF(any(CorrectionFactor.class));
        assertEquals(request, response);
    }
}
