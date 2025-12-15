package com.alquilaTusVehiculos.alquila_tus_vehiculos.repository;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
