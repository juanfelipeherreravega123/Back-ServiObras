package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Cotizacion;
import com.serviobra.demo.modelo.dto.UsuarioRequest;
import com.serviobra.demo.servicio.CotizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionControlador {

    @Autowired
    private CotizacionServicio servicio;

    // Obtener lista de cotizaciones
    @GetMapping
    public List<Cotizacion> listar() {
        return servicio.listar();
    }

    // Crear una nueva cotización
    @PostMapping
    public Cotizacion crear(@RequestBody Cotizacion c) {
        return servicio.crear(c);
    }

    // Llevar los items de la cotización al carrito
    @PostMapping("/{id}/llevar-a-carrito")
    public ResponseEntity<Void> llevarACarrito(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
        try {
            Long usuarioId = usuarioRequest.getUsuarioId(); // Obtener usuarioId desde el JSON
            servicio.llevarACarrito(id, usuarioId); // Llamar al servicio para llevar la cotización al carrito
            return ResponseEntity.ok().build(); // Respuesta exitosa
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // En caso de error
        }
    }

}
