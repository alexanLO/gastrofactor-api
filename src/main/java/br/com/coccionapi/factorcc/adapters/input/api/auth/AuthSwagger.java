package br.com.coccionapi.factorcc.adapters.input.api.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.LogoutRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.RefreshRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserLoginRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.response.AuthResponse;
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
                        @ApiResponse(responseCode = "201", description = "Cadastro de usuário realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequest request);

        @Operation(summary = "", description = "", method = "POST")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Login realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request);

        @Operation(summary = "", description = "", method = "POST")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Atualizando token realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest request);

                @Operation(summary = "", description = "", method = "POST")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Logout realizado com sucesso", content = @Content(schema = @Schema(implementation = CalculatorResponse.class))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida ou tipo de peso não suportado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                        @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
        })
        public ResponseEntity<Void> logout(@RequestBody LogoutRequest request);
}
