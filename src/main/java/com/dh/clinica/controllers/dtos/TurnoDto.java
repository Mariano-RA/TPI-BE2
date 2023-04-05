package com.dh.clinica.controllers.dtos;

import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class TurnoDto {
    private Long id;
    private LocalDate fechaTurno;
    private Paciente paciente;
    private Odontologo odontologo;
}



