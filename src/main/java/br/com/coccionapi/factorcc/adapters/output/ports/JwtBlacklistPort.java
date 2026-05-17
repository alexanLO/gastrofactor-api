package br.com.coccionapi.factorcc.adapters.output.ports;

import java.time.LocalDateTime;

public interface JwtBlacklistPort {

    void blacklist(String token, LocalDateTime expiresAt);

    boolean isBlacklisted(String token);
}
