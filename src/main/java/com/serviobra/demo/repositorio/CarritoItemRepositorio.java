package com.serviobra.demo.repositorio;

import com.serviobra.demo.modelo.CarritoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem, Long> {

    // Buscar items por carrito
    List<CarritoItem> findByIdCarrito(Long idCarrito);

    // Buscar items por cotización 
    List<CarritoItem> findByIdCotizacion(Long idCotizacion);
}
