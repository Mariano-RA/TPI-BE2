package com.dh.clinica.exception;

public class ResourceNotFoundException extends RuntimeException {
    private static final String ERROR = "404 not found";

    public ResourceNotFoundException (String errorDetails){
        super(ERROR + ", " + errorDetails);
    }
}
