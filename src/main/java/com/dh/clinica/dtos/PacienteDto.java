package com.dh.clinica.dtos;

import com.dh.clinica.models.Domicilio;
import com.dh.clinica.models.Turno;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class PacienteDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaRegistro;
    private Domicilio domicilio;
    @JsonIgnore
    private Set<Turno> turnos;
}
