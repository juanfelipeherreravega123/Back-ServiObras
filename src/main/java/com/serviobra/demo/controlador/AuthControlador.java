package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Usuario;
import com.serviobra.demo.modelo.dto.LoginDTO;
import com.serviobra.demo.servicio.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO body) {
        try {
            Usuario usuario = usuarioServicio.login(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(usuario);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
