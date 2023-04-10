package com.dh.clinica.service.impl;

import com.dh.clinica.dtos.Mapper;
import com.dh.clinica.dtos.TurnoDto;
import com.dh.clinica.dtos.TurnoDtoRegis;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.models.Turno;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.TurnoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;

    private final Mapper mapper;


    @Override
    public Turno registrar(TurnoDtoRegis turno) {
        try {

            Long idPaciente = Long.parseLong(turno.getIdPaciente());
            Long idOdontologo = Long.parseLong(turno.getIdOdontologo());

            if(pacienteRepository.existsById(idPaciente) == false){
                ResourceNotFoundException err = new ResourceNotFoundException("El paciente no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
            else if(odontologoRepository.existsById(idOdontologo) == false){
                ResourceNotFoundException err = new ResourceNotFoundException("El odontologo no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
            else{
                Turno turnoParaRegistrar = new Turno();
                turnoParaRegistrar.setFechaTurno(LocalDate.now());
                turnoParaRegistrar.setPaciente(pacienteRepository.getById(idPaciente));
                turnoParaRegistrar.setOdontologo(odontologoRepository.getById(idOdontologo));

                var resVal = turnoRepository.save(turnoParaRegistrar);
                log.atInfo().log("Se registro el turno");
                return resVal;
            }
        } catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el siguiente campo en su formato correcto: " + e.getMessage());
            log.atError().log(e.getMessage());
            throw err;
        }
    }

    @Override
    public Turno buscarPorId(String id) {
        try{
            Turno turno = turnoRepository.findById(Long.parseLong(id)).orElse(null);
            if(turno != null){
                log.atInfo().log("Consulta de turno por ID realizada con exito.");
                return turno;
            }
            else{
                ResourceNotFoundException nf = new ResourceNotFoundException("No se encontro un turno con el ID correspndiente.");
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
    public List<Turno> buscarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Turno actualizar(TurnoDtoRegis turno) {
        try {
            Long idPaciente = Long.parseLong(turno.getIdPaciente());
            Long idOdontologo = Long.parseLong(turno.getIdOdontologo());

            if(pacienteRepository.existsById(idPaciente) == false){
                ResourceNotFoundException err = new ResourceNotFoundException("El paciente no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
            else if(odontologoRepository.existsById(idOdontologo) == false){
                ResourceNotFoundException err = new ResourceNotFoundException("El odontologo no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
            else if(turnoRepository.existsById(turno.getId()) == false){
                ResourceNotFoundException err = new ResourceNotFoundException("El turno no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
            else{
                Turno turnoParaRegistrar = new Turno();
                turnoParaRegistrar.setId(turno.getId());
                turnoParaRegistrar.setFechaTurno(LocalDate.now());
                turnoParaRegistrar.setPaciente(pacienteRepository.getById(idPaciente));
                turnoParaRegistrar.setOdontologo(odontologoRepository.getById(idOdontologo));

                var resVal = turnoRepository.save(turnoParaRegistrar);
                log.atInfo().log("Se actualizo el turno");
                return resVal;
            }
        } catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el siguiente campo en su formato correcto: " + e.getMessage());
            log.atError().log(e.getMessage());
            throw err;
        }
    }

    @Override
    public void eliminar(String id) {

        try{
            Long.parseLong(id);

            if(turnoRepository.existsById(Long.parseLong(id)) == false){
                ResourceNotFoundException nt = new ResourceNotFoundException("El turno no se encuentra en la base de datos");
                log.atError().log(nt.getMessage());
                throw nt;
            }
            else{
                turnoRepository.deleteById(Long.parseLong(id));
                log.atInfo().log("Se elimino el turno.");
            }
        }
        catch (NumberFormatException e) {
            BadRequestException err = new BadRequestException("Por favor, corregi el campo ID en su formato correcto");
            log.atError().log(e.getMessage());
            throw err;
        }

    }
}
