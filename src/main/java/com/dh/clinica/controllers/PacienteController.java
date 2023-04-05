package com.dh.clinica.controllers;

import com.dh.clinica.controllers.dtos.Mapper;
import com.dh.clinica.controllers.dtos.PacienteDto;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.service.PacienteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable Long id) {

        try{
            PacienteDto response = mapper.toPacienteDto(pacienteService.buscarPorId(id));
            return ResponseEntity.ok(response);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<PacienteDto>> findAll(){

        return ResponseEntity.ok(pacienteService.buscarTodos().stream().map(paciente -> mapper.toPacienteDto(paciente)).toList());
    }


    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> actualizar(@RequestBody PacienteDto pacienteDto,@NonNull @PathVariable Long id) {

        Paciente paciente = mapper.toPaciente(pacienteDto);

        try{
            return ResponseEntity.ok(mapper.toPacienteDto(pacienteService.actualizar(paciente)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        try{
        pacienteService.eliminar(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El paciente fue eliminado");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
