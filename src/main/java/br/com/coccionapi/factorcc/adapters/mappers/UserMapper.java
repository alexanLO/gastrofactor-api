package br.com.coccionapi.factorcc.adapters.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import br.com.coccionapi.factorcc.adapters.output.persistence.entity.UserEntity;
import br.com.coccionapi.factorcc.domain.command.UserCommand;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserCommand toCommand(UserEntity entity);
}
