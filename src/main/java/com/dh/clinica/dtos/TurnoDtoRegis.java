package com.dh.clinica.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TurnoDtoRegis{
    private Long id;
    private LocalDate fechaTurno;
    private String idPaciente;
    private String idOdontologo;
}