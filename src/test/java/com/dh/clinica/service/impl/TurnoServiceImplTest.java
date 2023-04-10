package com.dh.clinica.service.impl;

import com.dh.clinica.dtos.TurnoDtoRegis;
import com.dh.clinica.models.Domicilio;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.models.Turno;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.repository.impl.PacienteRepository;
import com.dh.clinica.repository.impl.TurnoRepository;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest

class TurnoServiceImplTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarTest() {
        Odontologo odontologo = new Odontologo(null, "Mariano", "Rodriguez","1234", null);
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Pedro", "Gonzalez", "12456789", null, domicilio, null);

        Paciente resPaciente = pacienteService.registrar(paciente);
        Odontologo resOdontologo = odontologoService.registrar(odontologo);

        TurnoDtoRegis turno = new TurnoDtoRegis();
        turno.setIdOdontologo(resOdontologo.getId().toString());
        turno.setIdPaciente(resPaciente.getId().toString());

        Turno resVal = turnoService.registrar(turno);
        assertEquals(1, resVal.getId());
    }

    @Test
    @Order(2)
    void buscarPorIdTest() {
        assertNotNull(turnoService.buscarPorId("1"));
    }

    @Test
    @Order(3)
    void buscarTodosTest() {
        Odontologo odontologo = new Odontologo(null, "Mariano", "Rodriguez","1234", null);
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Pedro", "Gonzalez", "12456789", null, domicilio, null);

        Paciente resPaciente = pacienteService.registrar(paciente);
        Odontologo resOdontologo = odontologoService.registrar(odontologo);

        TurnoDtoRegis turno = new TurnoDtoRegis();
        turno.setIdOdontologo(resOdontologo.getId().toString());
        turno.setIdPaciente(resPaciente.getId().toString());

        turnoService.registrar(turno);
        List<Turno> listaTurnos = turnoService.buscarTodos();
        assertFalse(listaTurnos.isEmpty());
    }

    @Test
    @Order(4)
    void actualizarTest() {
        Odontologo odontologo = new Odontologo(null, "Mariano", "Rodriguez","1234", null);
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Pedro", "Gonzalez", "12456789", null, domicilio, null);

        Paciente resPaciente = pacienteService.registrar(paciente);
        Odontologo resOdontologo = odontologoService.registrar(odontologo);

        TurnoDtoRegis turno = new TurnoDtoRegis();
        turno.setIdOdontologo(resOdontologo.getId().toString());
        turno.setIdPaciente(resPaciente.getId().toString());
        Turno resVal = turnoService.registrar(turno);

        turno.setId(resVal.getId());
        turno.setIdPaciente("1");

        Turno resActualizacion = turnoService.actualizar(turno);
        assertEquals("1", resActualizacion.getPaciente().getId());


    }

    @Test
    @Order(5)
    void eliminarTest() {
        turnoService.eliminar("1");
        assertNull(turnoService.buscarPorId("1"));
    }
}