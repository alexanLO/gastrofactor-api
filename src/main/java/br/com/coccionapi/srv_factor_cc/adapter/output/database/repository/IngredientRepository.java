package br.com.coccionapi.srv_factor_cc.adapter.output.database.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coccionapi.srv_factor_cc.adapter.output.database.entity.IngredientEntity;

@Repository
public interface IngredientRepository extends JpaRepository<IngredientEntity, UUID> {

}
