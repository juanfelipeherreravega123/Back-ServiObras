package com.serviobra.demo.servicio;

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

        // Buscar o crear el carrito para el usuario
        Carrito carrito = carritoRepository.findById(usuarioId)
            .orElseGet(() -> {
                // Si no existe el carrito, creamos uno nuevo
                Carrito nuevoCarrito = new Carrito();
                nuevoCarrito.setId_usuario(usuarioId);
                return carritoRepository.save(nuevoCarrito);
            });

        // Iterar sobre los items de la cotización y agregarlos al carrito
        for (CarritoItem item : cotizacion.getItems()) {
            CarritoItem carritoItem = new CarritoItem();
            carritoItem.setId_carrito(carrito.getId_carrito());  // Asignamos el carrito
            carritoItem.setId_producto(item.getId_producto());  // Asignamos el producto
            carritoItem.setCantidad(item.getCantidad());        // Asignamos la cantidad
            carritoItem.setSubtotal(item.getSubtotal());        // Asignamos el subtotal
            carritoItem.setCotizacion(cotizacion);              // Asignamos la cotización

            // Agregamos el item al carrito
            carrito.agregarItem(carritoItem);
            carritoItemRepository.save(carritoItem); // Guardamos el item en la base de datos
        }

        // Guardamos el carrito con todos los items
        carritoRepository.save(carrito);
    }

    // Crear una nueva cotización
    public Cotizacion crear(Cotizacion c) {
        return cotizacionRepository.save(c);
    }

    // Listar todas las cotizaciones
    public List<Cotizacion> listar() {
        return cotizacionRepository.findAll();
    }
}


