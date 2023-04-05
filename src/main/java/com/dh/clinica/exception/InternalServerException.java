package com.dh.clinica.exception;

public class InternalServerException extends RuntimeException{
    private static final String ERROR = "500 Internal Server Error";

    public InternalServerException (String errorDetails){
        super(ERROR + ", " + errorDetails);
    }
}
