package br.com.coccionapi.factorcc.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserLoginRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.request.UserRegisterRequest;
import br.com.coccionapi.factorcc.adapters.input.api.auth.dto.response.AuthResponse;
import br.com.coccionapi.factorcc.adapters.output.persistence.entity.RefreshTokenEntity;
import br.com.coccionapi.factorcc.adapters.output.persistence.entity.UserEntity;
import br.com.coccionapi.factorcc.domain.command.UserLoginCommand;
import br.com.coccionapi.factorcc.domain.command.RefreshTokenCommand;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import br.com.coccionapi.factorcc.domain.model.AuthVO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "provider", ignore = true)
    UserCommand toRegisterRequest(UserRegisterRequest request);

    AuthResponse toResponse(AuthVO register);

    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(UserCommand command);

   

    UserLoginCommand toLoginRequest(UserLoginRequest request);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "user.name", source = "user.name")
    @Mapping(target = "user.email", source = "user.email")
    @Mapping(target = "user.password", source = "user.password")
    @Mapping(target = "user.profession", source = "user.profession")
    @Mapping(target = "user.provider", source = "user.provider")
    @Mapping(target = "user.role", source = "user.role")
    RefreshTokenEntity toRefreshTokenEntity(RefreshTokenCommand refreshToken);

    RefreshTokenCommand toRefreshTokenCommand(RefreshTokenEntity token);
}
