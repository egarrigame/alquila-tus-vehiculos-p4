package com.alquilaTusVehiculos.alquila_tus_vehiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alquiler_vehiculo")
public class AlquilerVehiculos {
    @EmbeddedId
    private AlquilerVehiculoId id = new AlquilerVehiculoId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("alquilerId")
    @JoinColumn(name = "alquiler_id")
    private Alquiler alquiler;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("vehiculoId")
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    private Double precioDiaAplicado;
    private Integer kmsRecorridos;

    public AlquilerVehiculos() {}

    public AlquilerVehiculos(Alquiler alquiler, Vehiculo vehiculo, Double precioDiaAplicado) {
        this.alquiler = alquiler;
        this.vehiculo = vehiculo;
        this.precioDiaAplicado = precioDiaAplicado;
        this.id = new AlquilerVehiculoId(alquiler.getId(), vehiculo.getId());
    }

    // Getters y setters
    public AlquilerVehiculoId getId() { return id; }
    public void setId(AlquilerVehiculoId id) { this.id = id; }

    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Double getPrecioDiaAplicado() { return precioDiaAplicado; }
    public void setPrecioDiaAplicado(Double precioDiaAplicado) { this.precioDiaAplicado = precioDiaAplicado; }
}
