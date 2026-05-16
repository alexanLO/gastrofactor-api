package br.com.coccionapi.factorcc.adapters.input.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.AuthResponse;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.input.api.calculator.dto.response.CalculatorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Ambiente responsável em gerenciar usuarios")
public interface AuthSwagger {

        @Operation(summary = "", description = "", method = "POST")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Cadastro de usuário realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Alimento não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequest request);
}
