package com.dh.clinica.service;

import com.dh.clinica.models.Turno;

import java.util.List;

public interface TurnoService {
    Turno registrar(Turno turno);
    Turno buscarPorId(Long id);
    List<Turno> buscarTodos();
    Turno actualizar(Turno turno);
    void eliminar(Long id);
}
