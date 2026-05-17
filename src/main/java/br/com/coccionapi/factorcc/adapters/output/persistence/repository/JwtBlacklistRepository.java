package br.com.coccionapi.factorcc.adapters.output.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.JwtBlacklistEntity;

@Repository
public interface JwtBlacklistRepository extends JpaRepository<JwtBlacklistEntity, UUID> {

    boolean existsByToken(String token);
}
