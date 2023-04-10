package com.dh.clinica.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDtoRegis {

    private String username;
    private String password;
    private String rol;
}
