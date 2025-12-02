package com.serviobra.demo.servicio;

import com.serviobra.demo.dto.CotizacionDTO;
import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.modelo.Cotizacion;
import com.serviobra.demo.repositorio.CarritoRepositorio;
import com.serviobra.demo.repositorio.CarritoItemRepositorio;
import com.serviobra.demo.repositorio.CotizacionRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionServicio {

    @Autowired
    private CotizacionRepositorio cotizacionRepository;

    @Autowired
    private CarritoRepositorio carritoRepository;

    @Autowired
    private CarritoItemRepositorio carritoItemRepository;

    // Llevar los items de la cotización al carrito
    public void llevarACarrito(Long cotizacionId, Long usuarioId) {

        // Recuperamos la cotización desde la base de datos
        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId)
            .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        // Buscar el carrito por ID de USUARIO
        Carrito carrito = carritoRepository.findById(usuarioId)
            .orElseGet(() -> {
                Carrito nuevo = new Carrito();
                nuevo.setIdUsuario(usuarioId);
                nuevo.setEstado("activo");
                return carritoRepository.save(nuevo);
            });

        // Iterar sobre los items de la cotización
        for (CarritoItem item : cotizacion.getItems()) {

            CarritoItem nuevoItem = new CarritoItem();

            nuevoItem.setIdCarrito(carrito.getId_carrito());
            nuevoItem.setIdProducto(item.getIdProducto());
            nuevoItem.setCantidad(item.getCantidad());
            nuevoItem.setSubtotal(item.getSubtotal());
            nuevoItem.setIdCotizacion(cotizacion.getId_cotizacion());

            carritoItemRepository.save(nuevoItem);
        }
    }

    // Crear cotización desde DTO
    public Cotizacion crearDesdeDTO(CotizacionDTO dto) {
        Cotizacion c = new Cotizacion();

        c.setId_usuario(dto.getIdUsuario());
        c.setId_carrito(dto.getIdCarrito());
        c.setValor_total(dto.getTotal());
        c.setEstado(dto.getEstado());
        c.setFecha_cotizacion(dto.getFecha());

        return cotizacionRepository.save(c);
    }

    // Crear cotización directo
    public Cotizacion crear(Cotizacion c) {
        return cotizacionRepository.save(c);
    }

    // Listar cotizaciones
    public List<Cotizacion> listar() {
        return cotizacionRepository.findAll();
    }
}
