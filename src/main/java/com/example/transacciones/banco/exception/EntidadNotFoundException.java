package com.example.transacciones.banco.exception;

public class EntidadNotFoundException extends RuntimeException{

    public EntidadNotFoundException(String message) {
        super(message);
    }
}
