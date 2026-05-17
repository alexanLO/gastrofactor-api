package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapters.mappers.AuthMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.RefreshTokenRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.RefreshTokenPort;
import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthPersistence implements RefreshTokenPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthMapper authMapper;

    @Override
    public RefreshTokenCommand save(RefreshTokenCommand refreshToken) {

        return authMapper
                .toRefreshTokenCommand(refreshTokenRepository.save(authMapper.toRefreshTokenEntity(refreshToken)));
    }

    @Override
    public Optional<RefreshTokenCommand> findByToken(String token) {
        return Optional.ofNullable(
                authMapper.toRefreshTokenCommand(refreshTokenRepository.findByToken(token)));
    }
}
