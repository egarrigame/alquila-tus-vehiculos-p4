package com.alquilaTusVehiculos.alquila_tus_vehiculos.model;

//clave compuesta para la tabla del auqilervehiculo (intermedia para la relación n-m)

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AlquilerVehiculoId implements Serializable {
    @Column(name = "alquiler_id")
    private Long alquilerId;

    @Column(name = "vehiculo_id")
    private Long vehiculoId;

    public AlquilerVehiculoId() {}

    public AlquilerVehiculoId(Long alquilerId, Long vehiculoId) {
        this.alquilerId = alquilerId;
        this.vehiculoId = vehiculoId;
    }

    public Long getAlquilerId() { return alquilerId; }
    public void setAlquilerId(Long alquilerId) { this.alquilerId = alquilerId; }

    public Long getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Long vehiculoId) { this.vehiculoId = vehiculoId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlquilerVehiculoId)) return false;
        AlquilerVehiculoId that = (AlquilerVehiculoId) o;
        return Objects.equals(alquilerId, that.alquilerId) && Objects.equals(vehiculoId, that.vehiculoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alquilerId, vehiculoId);
    }
}
