package com.alquilaTusVehiculos.alquila_tus_vehiculos.repository;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
