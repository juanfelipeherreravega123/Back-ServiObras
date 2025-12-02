package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.repositorio.CarritoItemRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoItemServicio {

    private final CarritoItemRepositorio repo;

    public CarritoItemServicio(CarritoItemRepositorio repo) {
        this.repo = repo;
    }

    public CarritoItem agregar(CarritoItem item) {
        return repo.save(item);
    }

    public List<CarritoItem> listarPorCarrito(Long idCarrito) {
        return repo.findByIdCarrito(idCarrito);
    }

    public List<CarritoItem> listarPorCotizacion(Long idCotizacion) {
        return repo.findByIdCotizacion(idCotizacion);
    }

    public CarritoItem actualizar(Long id, CarritoItem item) {
        CarritoItem existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Item no encontrado"));

        existente.setCantidad(item.getCantidad());
        existente.setSubtotal(item.getSubtotal());
        existente.setIdProducto(item.getIdProducto());
        existente.setIdCarrito(item.getIdCarrito());
        existente.setIdCotizacion(item.getIdCotizacion());

        return repo.save(existente);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
