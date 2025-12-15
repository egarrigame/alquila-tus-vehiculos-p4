package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.*;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ClienteRepository clienteRepo;
    private final VehiculoRepository vehiculoRepo;
    private final AlquilerRepository alquilerRepo;

    public AdminController(ClienteRepository clienteRepo,
                           VehiculoRepository vehiculoRepo,
                           AlquilerRepository alquilerRepo) {
        this.clienteRepo = clienteRepo;
        this.vehiculoRepo = vehiculoRepo;
        this.alquilerRepo = alquilerRepo;
    }

    @GetMapping("/clientes")
    public String listClientes(Model model) {
        model.addAttribute("clientes", clienteRepo.findAll());
        return "admin/clientes/list";
    }

    @GetMapping("/clientes/create")
    public String createClienteForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "admin/clientes/form";
    }

    @PostMapping("/clientes/save")
    public String saveCliente(@ModelAttribute Cliente cliente) {
        clienteRepo.save(cliente);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/clientes/edit/{id}")
    public String editClienteForm(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "admin/clientes/form";
    }

    @GetMapping("/clientes/delete/{id}")
    public String deleteCliente(@PathVariable Long id) {
        clienteRepo.deleteById(id);
        return "redirect:/admin/clientes";
    }

    @GetMapping("/vehiculos")
    public String listVehiculos(Model model) {
        model.addAttribute("vehiculos", vehiculoRepo.findAll());
        return "admin/vehiculos/list";
    }

    @GetMapping("/vehiculos/create")
    public String createVehiculoForm(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "admin/vehiculos/form";
    }

    @PostMapping("/vehiculos/save")
    public String saveVehiculo(@ModelAttribute Vehiculo vehiculo) {
        vehiculoRepo.save(vehiculo);
        return "redirect:/admin/vehiculos";
    }

    @GetMapping("/vehiculos/delete/{id}")
    public String deleteVehiculo(@PathVariable Long id) {
        vehiculoRepo.deleteById(id);
        return "redirect:/admin/vehiculos";
    }

    @GetMapping("/alquileres")
    public String listAlquileres(Model model) {
        model.addAttribute("alquileres", alquilerRepo.findAll());
        return "admin/alquileres/list";
    }

    @GetMapping("/alquileres/delete/{id}")
    public String deleteAlquiler(@PathVariable Long id) {
        alquilerRepo.deleteById(id);
        return "redirect:/admin/alquileres";
    }
}
