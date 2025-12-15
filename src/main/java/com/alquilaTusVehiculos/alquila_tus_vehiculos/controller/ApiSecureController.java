package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Alquiler;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Cliente;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Usuario;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Vehiculo;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.AlquilerRepository;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.ClienteRepository;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.UsuarioRepository;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.VehiculoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/secure")
@Tag(name = "API Segura", description = "Endpoints protegidos que requieren autenticación JWT")
@SecurityRequirement(name = "bearerAuth")
public class ApiSecureController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VehiculoRepository vehiculoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private AlquilerRepository alquilerRepository;

    // Endpoint 1.- Obtener perfil del usuario --------------------------------------------------------
    @GetMapping("/perfil")
    @Operation(summary = "Obtener perfil", description = "obtener el perfil del usuario autenticado")
    public ResponseEntity<?> getPerfilUsuario() {
        try {
            List<Usuario> usuarios = usuarioRepository.findAll();
            return ResponseEntity.ok(usuarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el perfil: " + e.getMessage());
        }
    }

    // Endpoint 2.- Obtener vehículos (user autenticado) ---------------------------------------------
    @GetMapping("/vehiculos")
    @Operation(summary = "Obtener vehiculos", description = "obtener vehiculos (usuario autenticado)")
    public ResponseEntity<?> getVehiculos() {
        try {
            List<Vehiculo> vehiculos = vehiculoRepository.findAll();
            return ResponseEntity.ok(vehiculos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener los vehiculos: " + e.getMessage());
        }
    }

    // Endpoint 3.- Crear cliente para admins --------------------------------------------------------
    @PostMapping("/clientes")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Crear cliente", description = "crear cliente admin users")
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteGuardado = clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteGuardado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear el cliente: " + e.getMessage());
        }
    }

    // Endpoint 3.- Obtener alquileres del user logged ------------------------------------------------
    @GetMapping("/mis-alquileres")
    @Operation(summary = "Log alquileres", description = "obtener alquileres (logged user)")
    public ResponseEntity<?> getMisAlquileres() {
        try {
            List<Alquiler> alquileres = alquilerRepository.findAll();
            return ResponseEntity.ok(alquileres);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener los alquileres: " + e.getMessage());
        }
    }

    // Endpoint 5.- Conteo para admin ------------------------------------------------------------------
    @GetMapping("/conteo")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Conteo", description = "obtener conteo de elementos (admin user)")
    public ResponseEntity<?> getConteo() {
        try {
            long totalClientes = clienteRepository.count();
            long totalVehiculos = vehiculoRepository.count();
            long totalAlquileres = alquilerRepository.count();

            return ResponseEntity.ok(String.format(
                    "Conteo: hay %d clientes, %d vehículos, %d alquileres",
                    totalClientes, totalVehiculos, totalAlquileres
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
