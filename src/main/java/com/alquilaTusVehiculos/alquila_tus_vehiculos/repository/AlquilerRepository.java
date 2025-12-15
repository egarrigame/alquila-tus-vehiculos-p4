package com.alquilaTusVehiculos.alquila_tus_vehiculos.repository;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Long> {
}
