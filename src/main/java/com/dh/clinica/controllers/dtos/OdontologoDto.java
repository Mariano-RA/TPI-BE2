package com.dh.clinica.controllers.dtos;

import com.dh.clinica.models.Turno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OdontologoDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Long matricula;
    @JsonIgnore
    private Set<Turno> turnos;
}
