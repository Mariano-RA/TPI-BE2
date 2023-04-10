package com.dh.clinica.service.impl;

import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.InternalServerException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PacienteServiceImpl implements PacienteService {
    private final PacienteRepository pacienteRepository;

    @Override
    public Paciente registrar(Paciente paciente) {

        try {
            paciente.setFechaRegistro(LocalDate.now());
            Long.parseLong(paciente.getDni());
            Long.parseLong(paciente.getDomicilio().getNumero());
            var resVal = pacienteRepository.save(paciente);
            log.atInfo().log("Se registro el paciente");
            return resVal;
        } catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el siguiente campo en su formato correcto: " + e.getMessage());
            log.atError().log(e.getMessage());
            throw err;
        }
    }

    @Override
    public Paciente buscarPorId(String id) {
        try{
            Paciente paciente = pacienteRepository.findById(Long.parseLong(id)).orElse(null);
            if(paciente != null){
                log.atInfo().log("Consulta de paciente por ID realizada con exito.");
                return paciente;
            }
            else{
                ResourceNotFoundException nf = new ResourceNotFoundException("No se encontro un paciente con el ID correspndiente.");
                log.atError().log(nf.getMessage());
                throw nf;
            }
        }catch (NumberFormatException ne){
            BadRequestException err =  new BadRequestException("El ID no es un numero");
            log.atError().log(err.getMessage());
            throw err;
        }
    }

    @Override
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente actualizar(Paciente paciente)  {
        try{
            Long.parseLong(paciente.getDni());
            Long.parseLong(paciente.getDomicilio().getNumero());
            if(paciente.getId() != null && pacienteRepository.existsById(paciente.getId())){
                Paciente resVal = pacienteRepository.save(paciente);
                log.atInfo().log("Se actualizo el paciente correctamente.");
                return resVal;
            }
            else{
                ResourceNotFoundException err = new ResourceNotFoundException("El paciente no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
        }catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el siguiente campo en su formato correcto: " + e.getMessage());
            log.atError().log(e.getMessage());
            throw err;
        }

    }

    @Override
    public void eliminar(String id) {
        try{
            Long.parseLong(id);

            if(pacienteRepository.existsById(Long.parseLong(id)) == false){
                ResourceNotFoundException nt = new ResourceNotFoundException("El paciente no se encuentra en la base de datos");
                log.atError().log(nt.getMessage());
                throw nt;
            }
            else{
                pacienteRepository.deleteById(Long.parseLong(id));
                log.atInfo().log("Se elimino el paciente.");
            }
        }
        catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el campo ID en su formato correcto");
            log.atError().log(e.getMessage());
            throw err;
        }

    }


}

