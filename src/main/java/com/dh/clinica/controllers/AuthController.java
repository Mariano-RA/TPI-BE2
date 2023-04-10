package com.dh.clinica.controllers;

import com.dh.clinica.dtos.AuthRequestDto;
import com.dh.clinica.jwt.JwtUtils;
import com.dh.clinica.models.Usuario;
import com.dh.clinica.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    @PreAuthorize("hasRole('USER)")
    @PreAuthorize("hasAuthority('ROLE_USER)")
     */

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDto request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(JwtUtils.generateJWT(user));
            /* return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(user))
                    .build(); */
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AuthRequestDto request) {
        try {
            if (userDetailsServiceImpl.loadUserByUsername(request.getUsername()) != null) throw new RuntimeException("El usuario ya existe.");
        } catch (UsernameNotFoundException err) {
            // EMPTY
        }
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, JwtUtils.generateJWT(userDetailsServiceImpl.save(usuario)))
                .build();
    }

}
