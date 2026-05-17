package br.com.coccionapi.factorcc.adapters.output.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.RefreshTokenEntity;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {

    RefreshTokenEntity findByToken(String token);

}
