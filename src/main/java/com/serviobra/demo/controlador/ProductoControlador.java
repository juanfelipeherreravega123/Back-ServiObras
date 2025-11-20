package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Producto;
import com.serviobra.demo.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoControlador {

    @Autowired
    private ProductoServicio servicio;

    @GetMapping
    public List<Producto> listar() {
        return servicio.listar();
    }

    @PostMapping
    public Producto crear(@RequestBody Producto p) {
        return servicio.crear(p);
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "Producto eliminado";
    }
}
