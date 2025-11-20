package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.modelo.Producto;
import com.serviobra.demo.repositorio.CarritoItemRepositorio;
import com.serviobra.demo.repositorio.CarritoRepositorio;
import com.serviobra.demo.repositorio.ProductoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoServicio {

    @Autowired
    private CarritoRepositorio carritoRepo;

    @Autowired
    private CarritoItemRepositorio itemRepo;

    @Autowired
    private ProductoRepositorio productoRepo;

    // Crear carrito (recibe un Carrito completo desde el controlador)
    public Carrito crearCarrito(Carrito c) {
        c.setFecha_creacion(new java.sql.Timestamp(System.currentTimeMillis()));
        c.setEstado("abierto");
        return carritoRepo.save(c);
    }

    // Obtener carrito por ID
    public Carrito obtener(Long carritoId) {
        return carritoRepo.findById(carritoId).orElse(null);
    }

    // Agregar producto al carrito
    public CarritoItem agregarProducto(Long carritoId, Long productoId, Integer cantidad) {

        Carrito carrito = carritoRepo.findById(carritoId).orElse(null);
        Producto producto = productoRepo.findById(productoId).orElse(null);

        if (carrito == null || producto == null) return null;

        CarritoItem item = new CarritoItem();
        item.setId_carrito(carritoId);
        item.setId_producto(productoId);
        item.setCantidad(cantidad);

        if (producto.getPrecio_unitario() != null && cantidad != null) {
            item.setSubtotal(producto.getPrecio_unitario() * cantidad);
        }

        return itemRepo.save(item);
    }
}


