package br.com.coccionapi.factorcc.adapter.output.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coccionapi.factorcc.adapter.output.database.entity.CookingFactorEntity;

public interface CookingFactorRepository extends JpaRepository<CookingFactorEntity, UUID> {

}
