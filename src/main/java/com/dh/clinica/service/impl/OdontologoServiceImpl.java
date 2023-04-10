package com.dh.clinica.service.impl;

import com.dh.clinica.exception.BadRequestException;
import com.dh.clinica.exception.InternalServerException;
import com.dh.clinica.exception.ResourceNotFoundException;
import com.dh.clinica.models.Odontologo;
import com.dh.clinica.repository.impl.OdontologoRepository;
import com.dh.clinica.service.OdontologoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OdontologoServiceImpl implements OdontologoService {

    private final OdontologoRepository odontologoRepository;
    @Override
    public Odontologo registrar(Odontologo odontologo) throws BadRequestException{

        try {
            Long.parseLong(odontologo.getMatricula());
            var odontologoRegistrado = odontologoRepository.save(odontologo);
            log.atInfo().log("Se registro el odontologo");
            return odontologoRegistrado;
        }
        catch (RuntimeException e){
            BadRequestException err =  new BadRequestException("La matricula no es un numero. Por favor, verifica tus datos!");
            log.atError().log(e.getMessage());
            throw err;
        }
    }

    @Override
    public Odontologo buscarPorId(String id) {
        try{
            Long.parseLong(id);
            Odontologo odontologo = odontologoRepository.findById(Long.parseLong(id)).orElse(null);
            if(odontologo != null){
                log.atInfo().log("Consulta de odontologo por ID realizada con exito.");
                return odontologo;
            }
            else{
                ResourceNotFoundException nf = new ResourceNotFoundException("No se encontro un odontologo con el ID correspndiente.");
                log.atError().log(nf.getMessage());
                throw nf;
            }
        }catch (NumberFormatException ne){
            BadRequestException err =  new BadRequestException("El ID no es un numero");
            log.atError().log(err.getMessage());
            throw err;
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Odontologo actualizar(Odontologo odontologo){
        try{
            Long.parseLong(odontologo.getMatricula());
            if(odontologo.getId() != null && odontologoRepository.existsById(odontologo.getId())){
                Odontologo resVal = odontologoRepository.save(odontologo);
                log.atInfo().log("Se actualizo el odontologo correctamente.");
                return resVal;
            }
            else{
                ResourceNotFoundException err = new ResourceNotFoundException("El odontologo no existe.");
                log.atError().log(err.getMessage());
                throw err;
            }
        }catch (NumberFormatException ex){
            InternalServerException iex = new InternalServerException("Alguno de los datos es incorrecto.");
            log.atError().log(iex.getMessage());
            throw iex;
        }
    }

    @Override
    public void eliminar(String id) {

        try{
            Long.parseLong(id);

            if(odontologoRepository.existsById(Long.parseLong(id)) == false){
                ResourceNotFoundException nt = new ResourceNotFoundException("El odontologo no se encuentra en la base de datos");
                log.atError().log(nt.getMessage());
                throw nt;
            }
            else{
                odontologoRepository.deleteById(Long.parseLong(id));
                log.atInfo().log("Se elimino el odontologo.");
            }
        }
        catch (BadRequestException e){
            BadRequestException err =  new BadRequestException("el ID no es un numero. Por favor, verifica tus datos!");
            log.atError().log(e.getMessage());
            throw err;
        }

    }
}
