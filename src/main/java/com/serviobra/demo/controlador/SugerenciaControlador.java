package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Sugerencia;
import com.serviobra.demo.servicio.SugerenciaServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sugerencias")
@CrossOrigin
public class SugerenciaControlador {

    private final SugerenciaServicio servicio;

    public SugerenciaControlador(SugerenciaServicio servicio) {
        this.servicio = servicio;
    }

    // ============================
    // GUARDAR SUGERENCIA (POST)
    // ============================
    @PostMapping
    public Sugerencia guardar(@RequestBody Sugerencia sugerencia) {

        // Si viene desde index (no hay usuario)
        if (sugerencia.getId_usuario() == null) {
            sugerencia.setOrigen("index");
        }

        return servicio.guardar(sugerencia);
    }

    // ============================
    // LISTAR TODAS LAS SUGERENCIAS (GET)
    // ============================
    @GetMapping
    public ResponseEntity<List<Sugerencia>> listar() {
        return ResponseEntity.ok(servicio.listar());
    }

    // ============================
    // ELIMINAR SUGERENCIA POR ID (DELETE)
    // ============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return ResponseEntity.ok("Sugerencia eliminada");
    }
}
