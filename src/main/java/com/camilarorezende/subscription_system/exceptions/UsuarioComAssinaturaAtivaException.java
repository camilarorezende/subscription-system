package com.camilarorezende.subscription_system.exceptions;

public class UsuarioComAssinaturaAtivaException extends RuntimeException{

    public UsuarioComAssinaturaAtivaException() {
        super("O usuario não pode ser deletado pois possui assinatura ativa!");
    }
}
