package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Cotizacion;
import com.serviobra.demo.servicio.CotizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionControlador {

    @Autowired
    private CotizacionServicio servicio;

    @GetMapping
    public List<Cotizacion> listar() {
        return servicio.listar();
    }

    @PostMapping
    public Cotizacion crear(@RequestBody Cotizacion c) {
        return servicio.crear(c);
    }
}
