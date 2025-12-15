package com.alquilaTusVehiculos.alquila_tus_vehiculos.service;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Usuario;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Correo del usuario no encontrado: " + email));

        return usuario;
    }
}
