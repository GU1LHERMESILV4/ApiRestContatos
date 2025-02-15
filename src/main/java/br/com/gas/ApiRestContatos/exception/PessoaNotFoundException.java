package br.com.gas.ApiRestContatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PessoaNotFoundException extends RuntimeException {
    public PessoaNotFoundException(String mensagem) {
        super(mensagem);
    }
}