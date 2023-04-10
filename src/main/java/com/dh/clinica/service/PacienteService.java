package com.dh.clinica.service;

import com.dh.clinica.models.Paciente;

import java.util.List;

public interface PacienteService {
    Paciente registrar(Paciente paciente);
    Paciente buscarPorId(String id);
    List<Paciente> buscarTodos();
    Paciente actualizar(Paciente paciente);
    void eliminar (String id);
}