package com.camilarorezende.subscription_system.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlanoComAssinaturaAtivaException extends RuntimeException {

    public PlanoComAssinaturaAtivaException() {
        super("O plano não pode ser deletado pois possui assinatura ativa!");
    }
}