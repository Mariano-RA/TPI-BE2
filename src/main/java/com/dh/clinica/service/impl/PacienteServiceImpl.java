package com.dh.clinica.service.impl;

import com.dh.clinica.models.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    @Override
    public Paciente registrar(Paciente paciente) {
        paciente.setFechaRegistro(LocalDate.now());
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente buscarPorId(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente) throws RuntimeException {

        if (paciente.getId() != null && pacienteRepository.existsById(paciente.getId()))
            return pacienteRepository.save(paciente);
        else
            throw new RuntimeException();
    }

    @Override
    public void eliminar(Long id) {pacienteRepository.deleteById(id);
    }


}

