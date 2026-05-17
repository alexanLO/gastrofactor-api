package br.com.coccionapi.factorcc.adapters.input.api.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.LogoutRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.RefreshRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserLoginRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.response.AuthResponse;
import br.com.coccionapi.factorcc.adapters.mappers.AuthMapper;
import br.com.coccionapi.factorcc.application.usecase.LoginUserUseCase;
import br.com.coccionapi.factorcc.application.usecase.LogoutUseCase;
import br.com.coccionapi.factorcc.application.usecase.RefreshTokenUseCase;
import br.com.coccionapi.factorcc.application.usecase.RegisterUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController implements AuthSwagger {

    private final AuthMapper authMapper;
    private final LoginUserUseCase loginUserUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final LogoutUseCase logoutUseCase;

    @Override
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequest request) {
        log.info("Chamando requisição para o cadastro do usuario com nome = {} e email = {}", request.getName(),
                request.getEmail());

        AuthResponse response = authMapper
                .toResponse(registerUserUseCase.register(authMapper.toRegisterRequest(request)));

        log.info("Usuário cadastrado com sucesso.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginRequest request) {
        log.info("Chamando requisição para logar usuario com email: {}", request.getEmail());

        AuthResponse response = authMapper.toResponse(loginUserUseCase.login(authMapper.toLoginRequest(request)));

        log.info("Usuário logado com sucesso.");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(RefreshRequest request) {
        log.info("Chamando requisição para atualizar o token");

        AuthResponse response = authMapper.toResponse(refreshTokenUseCase.refresh(request.getRefreshToken()));

        log.info("Token atualizado com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(LogoutRequest request) {
        log.info("Chamando requisição para logout.");

        logoutUseCase.logout(request.accessToken(), request.refreshToken());

        log.info("Logout realizado com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
