package com.dh.clinica.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity (name = "domicilios")
@Getter
@Setter
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domicilio_id")
    private Long id;
    private String calle;
    private String numero;
    private String ciudad;
    private String provincia;


}
