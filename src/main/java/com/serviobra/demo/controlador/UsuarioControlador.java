package com.serviobra.demo.controlador;

import com.serviobra.demo.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> body) {

        try {
            usuarioServicio.registrar(
                    body.get("nombre"),
                    body.get("apellido"),
                    body.get("email"),
                    body.get("password"),
                    body.get("username")
            );

            return ResponseEntity.ok(Map.of("message", "Código enviado al correo"));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/verificar")
    public ResponseEntity<?> verificar(@RequestBody Map<String, String> body) {

        boolean ok = usuarioServicio.verificar(body.get("email"), body.get("codigo"));

        if (ok) {
            return ResponseEntity.ok(Map.of("message", "Cuenta verificada con éxito"));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Código incorrecto"));
    }
}

