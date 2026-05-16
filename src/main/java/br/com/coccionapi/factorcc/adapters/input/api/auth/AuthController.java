package br.com.coccionapi.factorcc.adapters.input.api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.AuthResponse;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.mappers.UserMapper;
import br.com.coccionapi.factorcc.application.usecase.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController implements AuthSwagger {

    private final RegisterUserUseCase registerUserUseCase;
    private final UserMapper authMapper;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        log.info("Chamando requisição para o cadastro do usuario com nome = {} e email = {}", request.getName(),
                request.getEmail());

        AuthResponse response = authMapper
                .toResponse(registerUserUseCase.register(authMapper.toRequest(request)));

        log.info("Usuário cadastrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
