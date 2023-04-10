package com.dh.clinica.service.impl;

import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceImplTest {
    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void registrarTest() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Mariano");
        odontologo.setApellido("Rodriguez");
        odontologo.setMatricula("1234");
        Odontologo resVal = odontologoService.registrar(odontologo);
        assertEquals(1, resVal.getId());
    }

    @Test
    @Order(2)
    void buscarPorIdTest() {
        assertNotNull(odontologoService.buscarPorId("1").getId());
    }

    @Test
    @Order(3)
    void buscarTodosTest() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Valentin");
        odontologo.setApellido("Rodriguez");
        odontologo.setMatricula("5678");
        odontologoService.registrar(odontologo);

        List<Odontologo> listaOdontologos = odontologoService.buscarTodos();
        assertFalse(listaOdontologos.isEmpty());
    }

    @Test
    @Order(4)
    void actualizarTest() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Pedro");
        odontologo.setApellido("Picapiedra");
        odontologo.setMatricula("1973");
        Odontologo odontologoRegistrado = odontologoService.registrar(odontologo);

        odontologoRegistrado.setMatricula("5525");
        Odontologo resVal = odontologoService.actualizar(odontologoRegistrado);
        assertEquals("5525", resVal.getMatricula());
    }

    @Test
    @Order(5)
    void eliminarTest() {
        odontologoService.eliminar("1");
        assertThrows(ResourceNotFoundException.class, ()-> odontologoService.buscarPorId("1"), "El odontologo no se encuentra en la base de datos");
    }

    @Test
    @Order(6)
    void registrarConLetraEnMatriculaTest(){
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Diego");
        odontologo.setApellido("Maradona");
        odontologo.setMatricula("1234A");
        assertThrows(BadRequestException.class, ()-> odontologoService.registrar(odontologo),"La matricula no es un numero. Por favor, verifica tus datos!");

    }

    @Test
    @Order(7)
    void eliminarIdInexistenteTest(){
        assertThrows(ResourceNotFoundException.class, ()-> odontologoService.eliminar("6"),"El odontologo no se encuentra en la base de datos");

    }
}