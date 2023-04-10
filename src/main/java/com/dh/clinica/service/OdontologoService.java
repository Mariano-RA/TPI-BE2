package com.dh.clinica.service;

import com.dh.clinica.models.Odontologo;

import java.util.List;

public interface OdontologoService {

    Odontologo registrar(Odontologo odontologo);
    Odontologo buscarPorId(String id);
    List<Odontologo> buscarTodos();
    Odontologo actualizar(Odontologo odontologo);
    void eliminar(String id);

}