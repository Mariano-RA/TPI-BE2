package com.dh.clinica.controllers;

import com.dh.clinica.controllers.dtos.Mapper;
import com.dh.clinica.controllers.dtos.TurnoDto;
import com.dh.clinica.controllers.dtos.TurnoDtoRegis;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.models.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/turnos")
@RequiredArgsConstructor
public class TurnoController {

    @Autowired
    private final TurnoService turnoService;
    @Autowired
    private final PacienteService pacienteService;
    @Autowired
    private final OdontologoService odontologoService;
    private final Mapper mapper;

    @PostMapping(consumes = "application/json;charset=UTF-8")
    public ResponseEntity<TurnoDto> registrar(@RequestBody TurnoDtoRegis turnoDto) {

        TurnoDto response;

        try{
            Turno turnoParaCargar = new Turno();
            turnoParaCargar.setOdontologo(odontologoService.buscarPorId(turnoDto.getIdOdontologo()));
            turnoParaCargar.setFechaTurno(turnoDto.getFechaTurno());
            turnoParaCargar.setPaciente(pacienteService.buscarPorId(turnoDto.getIdPaciente()));
            turnoParaCargar.setId(turnoDto.getId());
            response = mapper.toTurnoDto(turnoService.registrar(turnoParaCargar));

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch(BadRequestException e){
            throw new BadRequestException("Alguno de los datos es incorrecto. "+ e);
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Odontologo o el Paciente no existen.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarPorId(@PathVariable Long id) {

        try{
            Turno resVal = turnoService.buscarPorId(id);
            TurnoDto response = mapper.toTurnoDto(resVal);
            return ResponseEntity.ok(response);
        }
        catch(BadRequestException e){
            throw new BadRequestException("El ID debe ser un numero");
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Turno no existe");
        }

    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos().stream().map(a -> mapper.toTurnoDto(a)).toList());
    }

    @PutMapping()
    public ResponseEntity<TurnoDto> actualizar(@RequestBody TurnoDtoRegis turnoDto) {
        try{
            Turno turnoParaActualizar = turnoService.buscarPorId(turnoDto.getId());
            turnoParaActualizar.setOdontologo(odontologoService.buscarPorId(turnoDto.getIdOdontologo()));
            turnoParaActualizar.setPaciente(pacienteService.buscarPorId(turnoDto.getIdPaciente()));
            Turno response = turnoService.actualizar(turnoParaActualizar);
            return ResponseEntity.ok(mapper.toTurnoDto(response));

        }
        catch(BadRequestException e){
            throw new BadRequestException("Alguno de los datos es incorrecto. "+ e);
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Odontologo o el Paciente no existen.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try{
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El turno fue eliminado");

        }
        catch(BadRequestException e){
            throw new BadRequestException("El ID debe ser un numero");
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Turno no existe");
        }
    }
}
