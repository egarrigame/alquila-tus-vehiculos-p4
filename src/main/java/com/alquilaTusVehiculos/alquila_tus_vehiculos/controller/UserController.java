package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.*;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.*;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.service.UsuarioService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final AlquilerRepository alquilerRepo;
    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;
    private final AlquilerVehiculoRepository avRepo;
    private final UsuarioService usuarioService;

    public UserController(AlquilerRepository alquilerRepo,
                          ClienteRepository clienteRepo,
                          VehiculoRepository vehiculoRepo,
                          AlquilerVehiculoRepository avRepo,
                          UsuarioService usuarioService) {
        this.alquilerRepo = alquilerRepo;
        this.clienteRepo = clienteRepo;
        this.vehiculoRepo = vehiculoRepo;
        this.avRepo = avRepo;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/alquileres")
    public String listAlquileres(Model model) {
        model.addAttribute("alquileres", alquilerRepo.findAll());
        return "user/alquileres/list";
    }

    @GetMapping("/alquileres/create")
    public String createAlquilerForm(Model model) {
        model.addAttribute("alquiler", new Alquiler());
        model.addAttribute("clientes", clienteRepo.findAll());
        model.addAttribute("vehiculos", vehiculoRepo.findAll());
        return "user/alquileres/form";
    }

    @PostMapping("/alquileres/save")
    public String saveAlquiler(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin,
            @RequestParam(value = "vehiculoIds", required = false) List<Long> vehiculoIds,
            Authentication authentication
    ) {

        String username = authentication.getName();
        Usuario usuario = usuarioService.buscarPorEmail(username);

        Alquiler alquiler = new Alquiler();
        alquiler.setFechaInicio(java.time.LocalDate.parse(fechaInicio));
        alquiler.setFechaFin(java.time.LocalDate.parse(fechaFin));

        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("El cliente no existe"));
        alquiler.setCliente(cliente);
        alquiler.setUsuario(usuario);

        alquiler = alquilerRepo.save(alquiler);

        double total = 0.0;
        if (vehiculoIds != null) {
            for (Long vid : vehiculoIds) {
                Vehiculo v = vehiculoRepo.findById(vid).orElseThrow();
                AlquilerVehiculos av = new AlquilerVehiculos();
                AlquilerVehiculoId id = new AlquilerVehiculoId();
                id.setAlquilerId(alquiler.getId());
                id.setVehiculoId(v.getId());
                av.setId(id);
                av.setAlquiler(alquiler);
                av.setVehiculo(v);
                av.setPrecioDiaAplicado(v.getPrecioDia());

                long dias = ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
                if (dias <= 0) dias = 1;
                total += (v.getPrecioDia() == null ? 0.0 : v.getPrecioDia()) * dias;

                avRepo.save(av);
            }
        }

        alquiler.setImporteTotal(total);
        alquilerRepo.save(alquiler);

        return "redirect:/user/alquileres";
    }
}
