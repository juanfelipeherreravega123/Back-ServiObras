package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.servicio.CarritoItemServicio;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito-items")
@CrossOrigin(origins = "*")
public class CarritoItemControlador {

    private final CarritoItemServicio servicio;

    public CarritoItemControlador(CarritoItemServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public CarritoItem crear(@RequestBody CarritoItem item) {
        return servicio.agregar(item);
    }

    @GetMapping("/carrito/{idCarrito}")
    public List<CarritoItem> listarPorCarrito(@PathVariable Long idCarrito) {
        return servicio.listarPorCarrito(idCarrito);
    }

    @GetMapping("/cotizacion/{idCotizacion}")
    public List<CarritoItem> listarPorCotizacion(@PathVariable Long idCotizacion) {
        return servicio.listarPorCotizacion(idCotizacion);
    }

    @PutMapping("/{id}")
    public CarritoItem actualizar(@PathVariable Long id, @RequestBody CarritoItem item) {
        return servicio.actualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
    }
}
