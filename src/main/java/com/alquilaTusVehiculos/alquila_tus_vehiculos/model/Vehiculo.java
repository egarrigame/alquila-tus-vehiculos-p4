package com.alquilaTusVehiculos.alquila_tus_vehiculos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private String modelo;
    private Double precioDia;

    public Vehiculo() {}
    public Vehiculo(String matricula, String modelo, Double precioDia) {
        this.matricula = matricula;
        this.modelo = modelo;
        this.precioDia = precioDia;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public Double getPrecioDia() { return precioDia; }
    public void setPrecioDia(Double precioDia) { this.precioDia = precioDia; }
}
