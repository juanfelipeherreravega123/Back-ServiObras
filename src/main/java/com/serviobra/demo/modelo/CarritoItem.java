package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "carrito_items")
public class CarritoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_item;

    private Long id_carrito;
    private Long id_producto;
    private Integer cantidad;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "id_carrito", referencedColumnName = "id_carrito", insertable = false, updatable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id_cotizacion", insertable = false, updatable = false)
    private Cotizacion cotizacion; // Relación con Cotización

    // Getters y setters
    public Long getId_item() { return id_item; }
    public void setId_item(Long id) { this.id_item = id; }

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id) { this.id_carrito = id; }

    public Long getId_producto() { return id_producto; }
    public void setId_producto(Long id) { this.id_producto = id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer c) { this.cantidad = c; }

    public Double getSubtotal() { return subtotal; }
    public void setSubtotal(Double s) { this.subtotal = s; }

    public Carrito getCarrito() { return carrito; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }

    public Cotizacion getCotizacion() { return cotizacion; }
    public void setCotizacion(Cotizacion cotizacion) { this.cotizacion = cotizacion; }  // Agregar este setter
}

 