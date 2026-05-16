package br.com.coccionapi.factorcc.adapters.output.persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.coccionapi.factorcc.adapters.mappers.UserMapper;
import br.com.coccionapi.factorcc.adapters.output.persistence.repository.UserRepository;
import br.com.coccionapi.factorcc.adapters.output.ports.UserPort;
import br.com.coccionapi.factorcc.domain.command.UserCommand;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserPersister implements UserPort {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public void register(UserCommand command) {

        userRepository.save(userMapper.toEntity(command));
    }

    @Override
    public Optional<UserCommand> findUserByEmail(String email) {
        return Optional.ofNullable(userMapper.toCommand(userRepository.findByEmail(email)));
    }
}
