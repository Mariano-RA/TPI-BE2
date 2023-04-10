package com.dh.clinica.controllers;

import com.dh.clinica.dtos.Mapper;
import com.dh.clinica.dtos.TurnoDto;
import com.dh.clinica.dtos.TurnoDtoRegis;
import com.dh.clinica.models.Turno;
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

    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<?> registrar(@RequestBody TurnoDtoRegis turno) {
        Turno resVal = turnoService.registrar(turno);
        return ResponseEntity.ok(resVal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Turno resVal = turnoService.buscarPorId(id);
        return ResponseEntity.ok(resVal);
    }

    @GetMapping
    public ResponseEntity<List<TurnoDto>> buscarTodos() {
        return ResponseEntity.ok(turnoService.buscarTodos().stream().map(a -> mapper.toTurnoDto(a)).toList());
    }

    @PutMapping()
    public ResponseEntity<?> actualizar(@RequestBody TurnoDtoRegis turnoDto) {
        Turno resVal = turnoService.actualizar(turnoDto);
        return ResponseEntity.ok(resVal);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) {
        turnoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El turno fue eliminado");
    }
}
