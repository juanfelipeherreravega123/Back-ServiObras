package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.servicio.CarritoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoControlador {

    @Autowired
    private CarritoServicio servicio;

    @PostMapping("/crear")
    public Carrito crear(@RequestBody Carrito c) {
        return servicio.crearCarrito(c);
    }

    @PostMapping("/{id}/agregar")
    public CarritoItem agregarProducto(
            @PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam int cantidad
    ) {
        return servicio.agregarProducto(id, productoId, cantidad);
    }

    @GetMapping("/{id}")
    public Carrito obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }
}


