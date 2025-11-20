package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Usuario;
import com.serviobra.demo.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    // ======================
    // REGISTRO
    // ======================
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Map<String, String> body) {

        String nombre = body.get("nombre");
        String apellido = body.get("apellido");
        String email = body.get("email");
        String password = body.get("password");
        String username = body.get("username");

        try {
            Usuario u = usuarioServicio.registrar(nombre, apellido, email, password, username);
            return ResponseEntity.ok("Código enviado al correo");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // ======================
    // VERIFICACIÓN
    // ======================
    @PostMapping("/verificar")
    public ResponseEntity<?> verificar(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String codigo = body.get("codigo");

        if (usuarioServicio.verificar(email, codigo)) {
            return ResponseEntity.ok("Cuenta verificada con éxito");
        } else {
            return ResponseEntity.badRequest().body("Código incorrecto");
        }
    }
}
