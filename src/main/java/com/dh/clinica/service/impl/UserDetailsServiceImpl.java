package com.dh.clinica.service.impl;

import com.dh.clinica.models.Usuario;
import com.dh.clinica.repository.impl.UsuarioRepository;
import com.dh.clinica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService, UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails save(UserDetails userDetails) {
        Usuario usuario = new Usuario();
        usuario.setUsername(userDetails.getUsername());
        usuario.setPassword(userDetails.getPassword());
        usuario.setAuthorities(new SimpleGrantedAuthority("ROLE_USER"));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("El usuario no existe."));
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(usuario.getAuthorities())
                .accountExpired(!usuario.isAccountNonExpired())
                .accountLocked(!usuario.isAccountNonLocked())
                .credentialsExpired(!usuario.isCredentialsNonExpired())
                .disabled(!usuario.isEnabled())
                .build();
    }

}
