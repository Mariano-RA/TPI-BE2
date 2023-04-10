package com.dh.clinica.dtos;

import com.dh.clinica.models.Odontologo;
import com.dh.clinica.models.Paciente;
import com.dh.clinica.models.Turno;

public class Mapper {

    public PacienteDto toPacienteDto (Paciente paciente){

        PacienteDto pacienteDto = new PacienteDto();

        pacienteDto.setId(paciente.getId());
        pacienteDto.setNombre(paciente.getNombre());
        pacienteDto.setApellido(paciente.getApellido());
        pacienteDto.setDni(paciente.getDni());
        pacienteDto.setFechaRegistro(paciente.getFechaRegistro());
        pacienteDto.setDomicilio(paciente.getDomicilio());
        pacienteDto.setTurnos(paciente.getTurnos());

        return pacienteDto;

    }

    public Paciente toPaciente(PacienteDto pacienteDto){

        Paciente paciente= new Paciente();

        paciente.setId(pacienteDto.getId());
        paciente.setNombre(pacienteDto.getNombre());
        paciente.setApellido(pacienteDto.getApellido());
        paciente.setDni(pacienteDto.getDni());
        paciente.setFechaRegistro(pacienteDto.getFechaRegistro());
        paciente.setDomicilio(pacienteDto.getDomicilio());
        paciente.setTurnos(pacienteDto.getTurnos());

        return paciente;
    }


    public OdontologoDto toOdontologoDto (Odontologo odontologo){

        OdontologoDto odontologoDto = new OdontologoDto();

        odontologoDto.setId(odontologo.getId());
        odontologoDto.setNombre(odontologo.getNombre());
        odontologoDto.setApellido(odontologo.getApellido());
        odontologoDto.setMatricula(odontologo.getMatricula());
        odontologoDto.setTurnos(odontologo.getTurnos());

        return odontologoDto;
    }

    public Odontologo toOdontologo (OdontologoDto odontologoDto){

        Odontologo odontologo = new Odontologo();

        odontologo.setId(odontologoDto.getId());
        odontologo.setNombre(odontologoDto.getNombre());
        odontologo.setApellido(odontologoDto.getApellido());
        odontologo.setMatricula(odontologoDto.getMatricula());
        odontologo.setTurnos(odontologoDto.getTurnos());

        return odontologo;
    }

    public TurnoDto toTurnoDto (Turno turno){

        TurnoDto turnoDto = new TurnoDto();

        turnoDto.setId(turno.getId());
        turnoDto.setFechaTurno(turno.getFechaTurno());
        turnoDto.setPaciente(turno.getPaciente());
        turnoDto.setOdontologo(turno.getOdontologo());

        return turnoDto;
    }

    public Turno toTurno (TurnoDto turnoDto){

        Turno turno = new Turno();

        turno.setId(turnoDto.getId());
        turno.setFechaTurno(turnoDto.getFechaTurno());
        turno.setPaciente(turnoDto.getPaciente());
        turno.setOdontologo(turnoDto.getOdontologo());

        return turno;
    }


}
