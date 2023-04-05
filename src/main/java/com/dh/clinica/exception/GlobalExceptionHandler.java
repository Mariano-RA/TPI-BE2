package com.dh.clinica.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public MensajeError notFound(HttpServletRequest request, ResourceNotFoundException ex){
        return MensajeError.builder()
                .mensaje(ex.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .url(request.getRequestURL().toString())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({BadRequestException.class, NumberFormatException.class})
    @ResponseBody
    public MensajeError badRequest(HttpServletRequest request, Exception ex){
        return MensajeError.builder()
                .mensaje(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .url(request.getRequestURL().toString())
                .build();
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public MensajeError internalServerError(HttpServletRequest request, Exception ex){
        return MensajeError.builder()
                .mensaje(ex.getMessage())
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .url(request.getRequestURL().toString())
                .build();
    }

}
