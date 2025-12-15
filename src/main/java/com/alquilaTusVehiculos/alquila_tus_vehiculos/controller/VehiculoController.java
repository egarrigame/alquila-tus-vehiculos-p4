package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Vehiculo;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vehiculos")
public class VehiculoController {
    private final VehiculoRepository vehiculoRepo;

    public VehiculoController(VehiculoRepository vehiculoRepo) {
        this.vehiculoRepo = vehiculoRepo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("vehiculos", vehiculoRepo.findAll());
        return "vehiculos/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("vehiculo", new Vehiculo());
        return "vehiculos/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Vehiculo vehiculo) {
        vehiculoRepo.save(vehiculo);
        return "redirect:/vehiculos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        vehiculoRepo.deleteById(id);
        return "redirect:/vehiculos";
    }
}
