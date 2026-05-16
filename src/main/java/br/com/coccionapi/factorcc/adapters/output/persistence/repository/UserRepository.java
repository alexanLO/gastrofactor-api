package br.com.coccionapi.factorcc.adapters.output.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    UserEntity findByEmail(String email);

}
