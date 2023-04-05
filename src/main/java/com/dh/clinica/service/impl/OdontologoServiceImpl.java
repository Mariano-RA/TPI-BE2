package com.dh.clinica.service.impl;

import com.dh.clinica.models.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OdontologoServiceImpl implements OdontologoService {

    private final OdontologoRepository odontologoRepository;


    @Override
    public Odontologo registrar(Odontologo dentist) {
        return odontologoRepository.save(dentist);
    }

    @Override
    public Odontologo buscarPorId(Long id) {
        return odontologoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo) throws RuntimeException{

        if (odontologo.getId() != null && odontologoRepository.existsById(odontologo.getId()))
            return odontologoRepository.save(odontologo);
        else
            throw new RuntimeException();

    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }
}
