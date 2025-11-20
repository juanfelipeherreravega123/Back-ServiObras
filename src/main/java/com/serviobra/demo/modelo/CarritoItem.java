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
}
