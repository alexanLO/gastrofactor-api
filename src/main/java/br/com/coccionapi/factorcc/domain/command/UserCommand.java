package br.com.coccionapi.factorcc.domain.command;

import br.com.coccionapi.factorcc.domain.enums.ProvidersEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCommand {

    private String name;
    private String email;
    private String password;
    private String profession;
    private ProvidersEnum provider;
    private String role;
}
