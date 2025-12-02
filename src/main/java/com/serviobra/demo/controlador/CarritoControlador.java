package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.servicio.CarritoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.serviobra.demo.modelo.CarritoItem;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoControlador {

    @Autowired
    private CarritoServicio servicio;

    // Crear carrito
    @PostMapping
    public Carrito crearCarrito(@RequestBody Carrito carrito) {
        return servicio.crearCarrito(carrito);
    }

    // Listar todos
    @GetMapping
    public List<Carrito> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    // Obtener por ID
    @GetMapping("/{id}")
    public Carrito obtenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    // Obtener por usuario
    @GetMapping("/usuario/{idUsuario}")
    public List<Carrito> obtenerPorUsuario(@PathVariable Long idUsuario) {
        return servicio.obtenerPorUsuario(idUsuario);
    }

    @PostMapping("/crear-completo")
    public Carrito crearCarritoCompleto(@RequestBody Carrito carrito) {

    // Fecha de creación por defecto
    carrito.setFecha_creacion(new java.sql.Timestamp(System.currentTimeMillis()));

    // Calcular total
    if (carrito.getItems() != null) {
        carrito.getItems().forEach(item -> {
            item.setIdCarrito(carrito.getId_carrito());
            item.setCarrito(carrito);
        });

        carrito.calcularTotal();
    }

    return servicio.crearCarrito(carrito);
}

    // Actualizar
    @PutMapping("/{id}")
    public Carrito actualizar(@PathVariable Long id, @RequestBody Carrito carrito) {
        return servicio.actualizarCarrito(id, carrito);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminarCarrito(id);
    }
	@PostMapping("/{id}/agregar-item")
    public Carrito agregarItem(@PathVariable Long id, @RequestBody CarritoItem item) {

    return servicio.agregarItem(id, item);
}

}
