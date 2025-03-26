package com.example.transacciones.banco.exception;

public class PersistenciaException extends  RuntimeException{

    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}
