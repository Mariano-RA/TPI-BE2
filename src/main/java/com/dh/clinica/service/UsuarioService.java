package com.dh.clinica.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {

    UserDetails save(UserDetails userDetails);

}
