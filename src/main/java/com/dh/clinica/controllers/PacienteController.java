package com.dh.clinica.controllers;

import com.dh.clinica.controllers.dtos.Mapper;
import com.dh.clinica.controllers.dtos.PacienteDto;
import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
@RequiredArgsConstructor
public class PacienteController {


    private final PacienteService pacienteService;

    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<PacienteDto> registrar(@RequestBody PacienteDto pacienteDto) {
        try{
        Paciente paciente = mapper.toPaciente(pacienteDto);
        PacienteDto response = mapper.toPacienteDto((pacienteService.registrar(paciente)));

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        catch(BadRequestException e){
            throw new BadRequestException("Alguno de los datos es incorrecto. "+ e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Long id) {
        try{
            PacienteDto response = mapper.toPacienteDto(pacienteService.buscarPorId(id));
            return ResponseEntity.ok(response);
        }
        catch(BadRequestException e){
            throw new BadRequestException("El ID debe ser un numero");
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Paciente no existe");
        }
    }


    @GetMapping
    public ResponseEntity<List<PacienteDto>> findAll(){

        return ResponseEntity.ok(pacienteService.buscarTodos().stream().map(paciente -> mapper.toPacienteDto(paciente)).toList());
    }


    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> actualizar(@RequestBody PacienteDto pacienteDto) {
        try{
            Paciente paciente = mapper.toPaciente(pacienteDto);
            return ResponseEntity.ok(mapper.toPacienteDto(pacienteService.actualizar(paciente)));
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Odontologo no existe.");
        }
        catch(BadRequestException e){
            throw new BadRequestException("Alguno de los datos es incorrecto.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        try{
        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El paciente fue eliminado");

        }
        catch(BadRequestException e){
            throw new BadRequestException("El ID debe ser un numero");
        }
        catch(NotFoundException e){
            throw new NotFoundException("El Odontologo no existe");
        }
    }
}
