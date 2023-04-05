package com.dh.clinica.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
public class MensajeError {
    private ZonedDateTime timeStamp;
    private HttpStatus httpStatus;
    private String mensaje;
    private String url;

    public MensajeError(ZonedDateTime timeStamp, HttpStatus httpStatus, String mensaje, String url) {
        this.timeStamp = ZonedDateTime.now();
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
        this.url = url;
    }
}
