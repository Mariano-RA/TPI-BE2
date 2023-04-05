package com.dh.clinica.controllers;

import com.dh.clinica.controllers.dtos.Mapper;
import com.dh.clinica.controllers.dtos.TurnoDto;
import com.dh.clinica.controllers.dtos.TurnoDtoRegis;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.models.Turno;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Paciente resPaciente = pacienteService.buscarPorId(turnoDto.getIdPaciente());
        Odontologo resOdontologo= odontologoService.buscarPorId(turnoDto.getIdodontologo());
        Turno turnoParaCargar = new Turno();
        turnoParaCargar.setOdontologo(resOdontologo);
        turnoParaCargar.setFechaTurno(turnoDto.getFechaTurno());
        turnoParaCargar.setPaciente(resPaciente);
        turnoParaCargar.setId(turnoDto.getId());

            if(resPaciente != null && resOdontologo != null){
                response = mapper.toTurnoDto(turnoService.registrar(turnoParaCargar));

                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarPorId(@PathVariable(name = "id") Long id) {

        try{
            Turno resVal = turnoService.buscarPorId(id);
            TurnoDto response = mapper.toTurnoDto(resVal);
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos().stream().map(a -> mapper.toTurnoDto(a)).toList());
    }

    @PutMapping()
    public ResponseEntity<TurnoDto> actualizar(@RequestBody TurnoDto turnoDto) {

        Turno turno = mapper.toTurno(turnoDto);

        try{

            return ResponseEntity.ok(mapper.toTurnoDto(turnoService.actualizar(turno)));

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        try{
        turnoService.eliminar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El turno fue eliminado");

        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


    }

}
