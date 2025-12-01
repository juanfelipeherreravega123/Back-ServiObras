package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Usuario;
import com.serviobra.demo.modelo.dto.LoginDTO;
import com.serviobra.demo.security.JwtUtil;
import com.serviobra.demo.servicio.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        try {
            // Validar credenciales
            Usuario usuario = usuarioServicio.login(body.getUsername(), body.getPassword());

            // Generar token JWT
            String token = jwtUtil.generateToken(usuario.getUsername());

            // Crear respuesta para el frontend
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", usuario.getUsername());
            response.put("id_usuario", usuario.getId_usuario());
            response.put("rol", usuario.getRol());
            response.put("estado", usuario.getEstado());

            return ResponseEntity.ok(response);

        } catch (RuntimeException ex) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}
