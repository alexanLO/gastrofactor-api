package br.com.coccionapi.srv_factor_cc.adapter.output.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.CookingFactorEntity;

public interface CookingFactorRepository extends JpaRepository<CookingFactorEntity, UUID> {

}
