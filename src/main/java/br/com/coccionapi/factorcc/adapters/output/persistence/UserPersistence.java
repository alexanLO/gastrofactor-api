package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapters.mappers.UserMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.UserRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPersistence implements UserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserCommand> findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.toCommand(userRepository.findByEmail(email)));
    }
}
