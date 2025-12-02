package com.serviobra.demo.controlador;

import com.serviobra.demo.repositorio.UsuarioRepositorio;
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
    private UsuarioRepositorio usuarioRepositorio;

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

    @PostMapping("/crear")
    public ResponseEntity<?> crearDesdeAdmin(@RequestBody Map<String, String> body) {

        try {
            usuarioServicio.crearDesdeAdmin(
                    body.get("nombre"),
                    body.get("apellido"),
                    body.get("email"),
                    body.get("username"),
                    body.get("password"),
                    body.get("rol")
            );

            return ResponseEntity.ok(Map.of("message", "Usuario creado exitosamente"));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(usuarioRepositorio.findAll());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {

        if (!usuarioRepositorio.existsById(id)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Usuario no encontrado"));
        }

        usuarioRepositorio.deleteById(id);

        return ResponseEntity.ok(Map.of("message", "Usuario eliminado correctamente"));
    }
     @GetMapping("/buscar/{texto}")
    public ResponseEntity<?> buscar(@PathVariable String texto) {
        var resultados = usuarioRepositorio.findByNombreContainingIgnoreCaseOrUsernameContainingIgnoreCase(texto, texto);
        return ResponseEntity.ok(resultados);
    }
}
