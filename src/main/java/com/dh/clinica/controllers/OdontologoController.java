package com.dh.clinica.controllers;

import com.dh.clinica.controllers.dtos.Mapper;
import com.dh.clinica.controllers.dtos.OdontologoDto;
import com.dh.clinica.exception.BadRequestException;
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

        try{
        Odontologo odontologo = mapper.toOdontologo(odontologoDto);

        OdontologoDto response = mapper.toOdontologoDto(odontologoService.registrar(odontologo));


        return ResponseEntity.ok(response);

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarPorId(@PathVariable(name = "id") String id) {
        try{
            OdontologoDto response = mapper.toOdontologoDto(odontologoService.buscarPorId(Long.parseLong(id)));
            return ResponseEntity.ok(response);
        }
        catch(Throwable e){
            throw new BadRequestException("El id debe ser un numero");
        }
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> buscarTodos(){

        return ResponseEntity.ok(odontologoService.buscarTodos().stream().map(odontologo -> mapper.toOdontologoDto(odontologo)).toList());
    }

    @PutMapping()
    public ResponseEntity<OdontologoDto> actualizar(@RequestBody OdontologoDto odontologoDto) {

        Odontologo odontologo = mapper.toOdontologo(odontologoDto);
        try{
            return ResponseEntity.ok(mapper.toOdontologoDto(odontologoService.actualizar(odontologo)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {



        try{
            odontologoService.eliminar(id);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se elimino al odontologo");

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
