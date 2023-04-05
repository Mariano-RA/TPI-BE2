package com.dh.clinica.repository.impl;

import com.dh.clinica.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}