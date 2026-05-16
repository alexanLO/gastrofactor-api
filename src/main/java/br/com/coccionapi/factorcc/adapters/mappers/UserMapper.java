package br.com.coccionapi.factorcc.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.AuthResponse;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.output.persistence.entity.UserEntity;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import br.com.coccionapi.factorcc.domain.model.AuthVO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "provider", ignore = true)
    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "email", source = "request.email")
    @Mapping(target = "password", source = "request.password")
    @Mapping(target = "profession", source = "request.profession")
    UserCommand toRequest(UserRegisterRequest request);

    AuthResponse toResponse(AuthVO register);

    UserEntity toEntity(UserCommand command);

    UserCommand toCommand(UserEntity entity);
}
