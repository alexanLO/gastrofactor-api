package br.com.coccionapi.srv_factor_cc.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException{
    
      public NotFoundException(String erroMsg) {
        super(HttpStatus.NOT_FOUND.value(), erroMsg);
    }
}
