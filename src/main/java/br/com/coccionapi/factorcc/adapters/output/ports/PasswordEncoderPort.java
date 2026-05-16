package br.com.coccionapi.factorcc.adapters.output.ports;

public interface PasswordEncoderPort {

    String encode(String rawPassword);

    boolean matches(String rawPassword, String encodedPassword);
}
