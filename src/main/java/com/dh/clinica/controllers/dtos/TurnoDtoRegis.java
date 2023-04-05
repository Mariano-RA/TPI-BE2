package com.dh.clinica.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TurnoDtoRegis{
    private Long id;
    private LocalDate fechaTurno;
    private Long idPaciente;
    private Long Idodontologo;
}