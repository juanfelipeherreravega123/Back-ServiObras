package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(unique = true)
    private String nombre;

    private String descripcion;
    private Double precio_unitario;
    private Integer stock;
    private String categoria;
    private String imagen_url;

    public Long getId_producto() { return id_producto; }
    public void setId_producto(Long id) { this.id_producto = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String n) { this.nombre = n; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }

    public Double getPrecio_unitario() { return precio_unitario; }
    public void setPrecio_unitario(Double p) { this.precio_unitario = p; }

    public Integer getStock() { return stock; }
    public void setStock(Integer s) { this.stock = s; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String c) { this.categoria = c; }

    public String getImagen_url() { return imagen_url; }
    public void setImagen_url(String i) { this.imagen_url = i; }
}
