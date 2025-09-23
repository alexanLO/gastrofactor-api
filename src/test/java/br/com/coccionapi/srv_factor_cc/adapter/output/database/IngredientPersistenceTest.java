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

import br.com.coccionapi.srv_factor_cc.adapter.output.database.mapper.EntityMapper;
import br.com.coccionapi.srv_factor_cc.adapter.output.database.repository.IngredientRepository;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as funcionalidades da camada de Persistência")
public class IngredientPersistenceTest extends IngredientMock{

    @Mock
    private IngredientRepository repository;

    @Mock
    private EntityMapper mapper;

    @InjectMocks
    private IngredientPersistence persistence;

    @Test
    @DisplayName("Deve salvar usuário no banco de dados")
    void mustRegister() {

        var expected = createIngredientFaker();
        var result = createIngredientEntityFaker();

        when(repository.save(result)).thenReturn(result);
        when(mapper.toSaveEntity(expected)).thenReturn(result);
        
        persistence.register(expected);

        verify(repository, times(1)).save(result);
        assertEquals(expected.getName(), result.getName());
    }
}
