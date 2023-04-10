package com.dh.clinica.controllers;

import com.dh.clinica.dtos.Mapper;
import com.dh.clinica.dtos.PacienteDto;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        Paciente paciente = mapper.toPaciente(pacienteDto);
        PacienteDto response = mapper.toPacienteDto((pacienteService.registrar(paciente)));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPorId(@PathVariable String id) {
        PacienteDto response = mapper.toPacienteDto(pacienteService.buscarPorId(id));
        return ResponseEntity.ok(response);

    }


    @GetMapping
    public ResponseEntity<List<PacienteDto>> findAll() {
        return ResponseEntity.ok(pacienteService.buscarTodos().stream().map(paciente -> mapper.toPacienteDto(paciente)).toList());
    }


    @PutMapping()
    public ResponseEntity<PacienteDto> actualizar(@RequestBody PacienteDto pacienteDto) {

        Paciente paciente = mapper.toPaciente(pacienteDto);
        return ResponseEntity.ok(mapper.toPacienteDto(pacienteService.actualizar(paciente)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {

        pacienteService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El paciente fue eliminado");

    }
}
