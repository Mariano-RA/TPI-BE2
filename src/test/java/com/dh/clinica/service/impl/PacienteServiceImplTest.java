package com.dh.clinica.service.impl;

import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.models.Domicilio;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceImplTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void registrarTest() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Pedro", "Gonzalez", "12456789", null, domicilio, null);

        Paciente resVal = pacienteService.registrar(paciente);
        assertEquals(1, resVal.getId());

    }

    @Test
    @Order(2)
    void buscarPorIdTest() {
        assertNotNull(pacienteService.buscarPorId("1").getId());
    }

    @Test
    @Order(3)
    void buscarTodosTest() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Ariel", "Diaz", "12456789", null, domicilio, null);

        pacienteService.registrar(paciente);
        List<Paciente> listaPacientes = pacienteService.buscarTodos();
        assertFalse(listaPacientes.isEmpty());

    }

    @Test
    @Order(4)
    void actualizarTest() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Ariel", "Diaz", "12456789", null, domicilio, null);

        Paciente pacienteRegistrado = pacienteService.registrar(paciente);
        pacienteRegistrado.setNombre("Matias");
        Paciente resVal = pacienteService.actualizar(pacienteRegistrado);
        assertEquals("Matias", resVal.getNombre());
    }

    @Test
    @Order(5)
    void eliminarTest() {
        pacienteService.eliminar("1");
        assertThrows(ResourceNotFoundException.class, ()-> pacienteService.buscarPorId("1"),"El paciente no se encuentra en la base de datos");
    }

    @Test
    @Order(6)
    void buscarPorIdInexistenteTest() {
        assertThrows(ResourceNotFoundException.class, ()-> pacienteService.buscarPorId("10"),"No se encontro un paciente con el ID correspndiente.");

    }

    @Test
    @Order(7)
    void cargaDeDatosErroneaTest() {
        Domicilio domicilio = new Domicilio();
        domicilio.setCalle("Av Patria");
        domicilio.setNumero("126");
        domicilio.setCiudad("Cordoba");
        domicilio.setProvincia("Cordoba");
        Paciente paciente = new Paciente(null, "Ariel", "Diaz", "12456789", null, domicilio, null);

        Paciente pacienteRegistrado = pacienteService.registrar(paciente);
        pacienteRegistrado.setDni("1345A");

        assertThrows(BadRequestException.class, ()-> pacienteService.actualizar(pacienteRegistrado));

    }
}