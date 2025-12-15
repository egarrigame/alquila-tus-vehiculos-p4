package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Cliente;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.ClienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository clienteRepo;

    public ClienteController(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clientes", clienteRepo.findAll());
        return "clientes/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Cliente cliente) {
        clienteRepo.save(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Cliente cliente = clienteRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado: " + id));
        model.addAttribute("cliente", cliente);
        return "clientes/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clienteRepo.deleteById(id);
        return "redirect:/clientes";
    }
}
