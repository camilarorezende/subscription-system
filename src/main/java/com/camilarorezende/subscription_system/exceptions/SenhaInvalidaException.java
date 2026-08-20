package com.camilarorezende.subscription_system.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class SenhaInvalidaException extends RuntimeException{

    public SenhaInvalidaException() {
        super("A senha deve ter pelo menos 6 caracteres!");
    }
}
