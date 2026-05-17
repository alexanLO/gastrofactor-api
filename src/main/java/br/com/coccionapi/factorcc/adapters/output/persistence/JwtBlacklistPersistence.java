package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.JwtBlacklistEntity;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.JwtBlacklistRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.JwtBlacklistPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtBlacklistPersistence implements JwtBlacklistPort {

    private final JwtBlacklistRepository jwtBlacklistRepository;

    @Override
    public void blacklist(String token, LocalDateTime expiresAt) {

        JwtBlacklistEntity entity = JwtBlacklistEntity.builder()
                .token(token)
                .expiresAt(expiresAt)
                .build();

        jwtBlacklistRepository.save(entity);
    }

    @Override
    public boolean isBlacklisted(String token) {
        return jwtBlacklistRepository.existsByToken(token);
    }

}
