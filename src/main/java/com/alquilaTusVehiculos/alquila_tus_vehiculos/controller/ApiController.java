package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Alquiler;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Cliente;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.AlquilerRepository;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "API consulta", description = "endpoints para consultar datos")
public class ApiController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AlquilerRepository alquilerRepository;

    @GetMapping("/clientes")
    @Operation(summary = "obtener clientes", description = "devolver json clientes")
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/alquileres")
    @Operation(summary = "obtener alquileres", description = "devolver json alquileres")
    public List<Alquiler> getAllAlquileres() {
        List<Alquiler> alquileres = alquilerRepository.findAll();
        for (Alquiler alquiler : alquileres) {
            if (alquiler.getCliente() != null) {
                alquiler.getCliente().getNombre();
            }
        }
        return alquileres;
    }
}
