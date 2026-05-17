package br.com.coccionapi.factorcc.domain.command;

import java.time.LocalDateTime;

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
public class RefreshTokenCommand {

   private String token;
   private LocalDateTime expiresAt;
   private boolean revoked;
   private UserCommand user;
}
