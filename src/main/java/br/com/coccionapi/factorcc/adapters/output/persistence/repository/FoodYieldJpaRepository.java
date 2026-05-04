package br.com.coccionapi.factorcc.adapters.output.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.FoodYieldEntity;

public interface FoodYieldJpaRepository extends JpaRepository<FoodYieldEntity, Long> {

    FoodYieldEntity findByFoodNameIgnoreCase(String foodName);

}
