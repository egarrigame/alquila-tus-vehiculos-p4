package com.alquilaTusVehiculos.alquila_tus_vehiculos.repository;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.AlquilerVehiculos;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.AlquilerVehiculoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlquilerVehiculoRepository extends JpaRepository<AlquilerVehiculos, AlquilerVehiculoId> {
}
