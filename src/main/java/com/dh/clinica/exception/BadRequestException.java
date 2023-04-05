package com.dh.clinica.exception;

public class BadRequestException extends RuntimeException{
    private static final String ERROR = "400 bad request";

    public BadRequestException (String errorDetails){
        super(ERROR + ", " + errorDetails);
    }
}
