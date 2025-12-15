package com.alquilaTusVehiculos.alquila_tus_vehiculos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;
    private String telefono;
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alquiler> alquileres = new ArrayList<>();

    // Constructores
    public Cliente() {}
    public Cliente(String dni, String telefono, String nombre) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Alquiler> getAlquileres() { return alquileres; }
    public void setAlquileres(List<Alquiler> alquileres) { this.alquileres = alquileres; }
}
