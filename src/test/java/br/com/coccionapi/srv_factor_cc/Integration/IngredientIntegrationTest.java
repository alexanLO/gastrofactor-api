package br.com.coccionapi.srv_factor_cc.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coccionapi.factorcc.adapter.input.dto.responses.CookingFactorResponse;
import br.com.coccionapi.factorcc.adapter.input.dto.responses.CorrectionFactorResponse;
import br.com.coccionapi.factorcc.adapter.input.dto.responses.IngredientResponse;
import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Fluxo de Integração do Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IngredientIntegrationTest extends IngredientMock {

    private static final String URL_BASE = "/v1/ingredient";
    private static final String URL_CORRECTION_FACTOR = URL_BASE + "/{id}/correction-factor";
    private static final String URL_COOKING_FACTOR = URL_BASE + "/{id}/cooking-factor";

    private IngredientResponse ingredient;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {

        objectMapper.findAndRegisterModules();

        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                // * Sera usado futuramente quando implementar autenticação
                // * .apply(SecurityMockMvcConfigurers.springSecurity())
                .defaultResponseCharacterEncoding(StandardCharsets.UTF_8)
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Registrar ingrediente.")
    // * Será usado quando implementar autenticação @WithMockUser(value = "admin",
    // * authorities = "ROLE_ADMIN")
    void mustRegisterIngredient() throws Exception {

        var request = createIngredientRequestFaker();

        var expectedJson = objectMapper.writeValueAsString(request);

        MvcResult result = mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson))
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        ingredient = objectMapper.readValue(responseJson, IngredientResponse.class);
    }

    @Test
    @Order(2)
    @DisplayName("Registrar calculo do fator de correção.")
    void mustRegisterCorrectionFactor() throws Exception {

        MvcResult result = mockMvc.perform(get(URL_CORRECTION_FACTOR, ingredient.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        CorrectionFactorResponse actual = objectMapper.readValue(responseJson, CorrectionFactorResponse.class);

        // Valida os campos dinamicamente
        assertEquals(ingredient.getId(), actual.getIngredientId());

        // Comparações de BigDecimal com tolerância
        assertTrue(ingredient.getGrossWeight().compareTo(actual.getGrossWeight()) == 0);
        assertTrue(ingredient.getNetWeight().compareTo(actual.getNetWeight()) == 0);

        // Verificações de campos dinâmicos
        assertNotNull(actual.getCorrectionFactor());
        assertNotNull(actual.getCalculatedAt());

    }

    @Test
    @Order(3)
    @DisplayName("Registrar calculo do fator de cocção.")
    void mustRegisterCookingFactor() throws Exception {

        MvcResult result = mockMvc.perform(get(URL_COOKING_FACTOR, ingredient.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String responseJson = result.getResponse().getContentAsString();
        CookingFactorResponse actual = objectMapper.readValue(responseJson, CookingFactorResponse.class);

        // Valida os campos dinamicamente
        assertEquals(ingredient.getId(), actual.getIngredientId());

        // Comparações de BigDecimal com tolerância
        assertTrue(ingredient.getRawWeight().compareTo(actual.getRawWeight()) == 0);
        assertTrue(ingredient.getCookedWeight().compareTo(actual.getCookedWeight()) == 0);

        // Verificações de campos dinâmicos
        assertNotNull(actual.getCookingFactor());
        assertNotNull(actual.getCalculatedAt());
    }
}
