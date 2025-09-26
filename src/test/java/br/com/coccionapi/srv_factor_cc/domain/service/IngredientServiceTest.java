package br.com.coccionapi.srv_factor_cc.domain.service;

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

import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;
import br.com.coccionapi.srv_factor_cc.port.output.IngredientPort;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as regras de negócios da camada service")
public class IngredientServiceTest extends IngredientMock {

    @Mock
    private IngredientPort ingredientPort;

    @InjectMocks
    private IngredientService service;

    @Test
    @DisplayName("Deve registrar um novo ingrediente com sucesso")
    void mustRegister() {

        var request = mock(Ingredient.class);

        when(ingredientPort.register(any(Ingredient.class))).thenReturn(createIngredientFaker());

        service.register(request);

        verify(ingredientPort, times(1)).register(request);
    }
}
