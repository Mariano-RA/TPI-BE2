package com.dh.clinica.service.impl;

import com.dh.clinica.models.Turno;
import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;


    @Override
    public Turno registrar(Turno turno) {
        turno.setFechaTurno(LocalDate.now());
        return turnoRepository.save(turno);
    }

    @Override
    public Turno buscarPorId(Long id) {
        return turnoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno actualizar(Turno turno) throws RuntimeException{

        if (turno.getId() != null && turnoRepository.existsById(turno.getId()))
            return turnoRepository.save(turno);
        else
            throw new RuntimeException();

    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
}
