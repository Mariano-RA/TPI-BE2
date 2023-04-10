package com.dh.clinica.service;

import com.dh.clinica.dtos.TurnoDto;
import com.dh.clinica.dtos.TurnoDtoRegis;
import com.dh.clinica.models.Turno;

import java.util.List;

public interface TurnoService {
    Turno registrar(TurnoDtoRegis turno);
    Turno buscarPorId(String id);
    List<Turno> buscarTodos();
    Turno actualizar(TurnoDtoRegis turno);
    void eliminar(String id);
}
