package com.dh.clinica.controllers;

import com.dh.clinica.dtos.Mapper;
import com.dh.clinica.dtos.OdontologoDto;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/odontologos")
@RequiredArgsConstructor
public class OdontologoController {

    private final OdontologoService odontologoService;
    private final Mapper mapper;

    @PostMapping()
    public ResponseEntity<OdontologoDto> registrar(@RequestBody OdontologoDto odontologoDto) {
        Odontologo odontologo = mapper.toOdontologo(odontologoDto);
        return ResponseEntity.ok(mapper.toOdontologoDto(odontologoService.registrar(odontologo)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarPorId(@PathVariable String id) {
        OdontologoDto response = mapper.toOdontologoDto(odontologoService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> buscarTodos() {
        return ResponseEntity.ok(odontologoService.buscarTodos().stream().map(odontologo -> mapper.toOdontologoDto(odontologo)).toList());
    }

    @PutMapping()
    public ResponseEntity<OdontologoDto> actualizar(@RequestBody OdontologoDto odontologoDto) {
        Odontologo odontologo = mapper.toOdontologo(odontologoDto);
        return ResponseEntity.ok(mapper.toOdontologoDto(odontologoService.actualizar(odontologo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {
        odontologoService.eliminar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se elimino al odontologo.");
    }
}
