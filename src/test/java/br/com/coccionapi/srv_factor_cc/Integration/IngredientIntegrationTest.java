package br.com.coccionapi.srv_factor_cc.Integration;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coccionapi.srv_factor_cc.mocks.IngredientMock;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Fluxo de Integração do Usuário")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class IngredientIntegrationTest extends IngredientMock {

    private static final String URL_BASE = "/v1/ingredient";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setup() throws Exception {
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
    void mustRegister() throws Exception {

        var request = createIngredientRequestFaker();

        var expectedJson = objectMapper.writeValueAsString(request);

        mockMvc.perform(post(URL_BASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}
