package br.com.coccionapi.srv_factor_cc.adapter.input.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coccionapi.srv_factor_cc.adapter.input.dto.requests.IngredientRequest;
import br.com.coccionapi.srv_factor_cc.adapter.input.mapper.IngredientMapper;
import br.com.coccionapi.srv_factor_cc.domain.model.CorrectionFactor;
import br.com.coccionapi.srv_factor_cc.domain.model.Ingredient;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;
import br.com.coccionapi.srv_factor_cc.port.input.CorrectionFactorUseCase;
import br.com.coccionapi.srv_factor_cc.port.input.IngredientUseCase;

@ExtendWith(MockitoExtension.class)
@DisplayName("Valida as funcionalidades da camada controller")
public class IngredientControllerTest extends IngredientMock {

    private static final String URL_BASE = "/v1/ingredient";
    private static final String URL_FATOR_CORRECAO = URL_BASE + "/{id}/correction-factor";

    @Mock
    private IngredientUseCase ingredientUseCase;

    @Mock
    private CorrectionFactorUseCase correctionFactorUseCase;

    @Mock
    private IngredientMapper mapper;

    @InjectMocks
    private IngredientController controller;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {

        ingredientUseCase = mock(IngredientUseCase.class);
        correctionFactorUseCase = mock(CorrectionFactorUseCase.class);
        mapper = mock(IngredientMapper.class);
        
        controller = new IngredientController(ingredientUseCase, correctionFactorUseCase, mapper);
        
        objectMapper.findAndRegisterModules();

        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                // .setControllerAdvice(BusinessExceptionHandler.class)
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    @Test
    @DisplayName("POST - cria igrediente → retorna 201.")
    void mustRegister() throws Exception {

        var request = createIngredientFaker();

        when(ingredientUseCase.register(any(Ingredient.class))).thenReturn(createIngredientFaker());
        when(mapper.toModelRequest(any(IngredientRequest.class))).thenReturn(request);

        mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        ArgumentCaptor<Ingredient> captor = ArgumentCaptor.forClass(Ingredient.class);
        verify(ingredientUseCase, times(1)).register(captor.capture());
        assertEquals(request, captor.getValue());
    }

    @Test
    @DisplayName("GET - calcular o fator de correcao → retorna 200.")
    void mustCalculateCorrectionFactor() throws Exception {

        var expectedJson = createCorrectionFactorFaker();

        when(mapper.toCorrectionFactorResponse(any(CorrectionFactor.class)))
                .thenReturn(createCorrectionFactorResponseFaker());
        when(correctionFactorUseCase.calculateCorrectionFactor(any(UUID.class)))
                .thenReturn(createCorrectionFactorFaker());

        mockMvc.perform(get(URL_FATOR_CORRECAO, ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedJson)));
    }
}
