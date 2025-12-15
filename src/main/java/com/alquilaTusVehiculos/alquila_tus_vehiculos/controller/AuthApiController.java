package com.alquilaTusVehiculos.alquila_tus_vehiculos.controller;

import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.dto.AuthRequest;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.dto.AuthResponse;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.model.Usuario;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.security.JwtUtil;
import com.alquilaTusVehiculos.alquila_tus_vehiculos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Endpoints para autenticación con JWT")
public class AuthApiController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Autentica un usuario y devuelve un token JWT")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate( // autenticación
                    new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
            );

            // detalles de usuario y generar token
            final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            final String jwt = jwtUtil.generateToken(userDetails);

            Usuario usuario = usuarioService.buscarPorEmail(authRequest.getEmail());

            return ResponseEntity.ok(new AuthResponse( // token de respuesta
                    jwt,
                    usuario.getEmail(),
                    usuario.getNombre(),
                    usuario.getRol()
            ));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al autenticar: " + e.getMessage());
        }
    }

    @GetMapping("/validate")
    @Operation(summary = "Validar token", description = "valida si el token es valido")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                String username = jwtUtil.extractUsername(token);
                return ResponseEntity.ok("el token es valido para el user:: " + username);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body("el token no es válido: " + e.getMessage());
            }
        }
        return ResponseEntity.badRequest().body("autorización header no es válida");
    }
}
