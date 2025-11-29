package com.serviobra.demo.modelo;

import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    @Column(unique = true, nullable = false) // Aseguramos que el nombre sea único y no nulo
    @NotNull(message = "El nombre no puede ser nulo.")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres.")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede tener más de 500 caracteres.")
    private String descripcion;

    @Column(nullable = false) // Aseguramos que el precio no pueda ser nulo
    @NotNull(message = "El precio unitario no puede ser nulo.")
    private Double precio_unitario;

    @Column(nullable = false) // Aseguramos que el stock no pueda ser nulo
    @NotNull(message = "El stock no puede ser nulo.")
    private Integer stock;

    @Size(max = 50, message = "La categoría no puede tener más de 50 caracteres.")
    private String categoria;

    @Size(max = 255, message = "La URL de la imagen no puede tener más de 255 caracteres.")
    private String imagen_url;

    // Getters y setters
    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(Double precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen_url() {
        return imagen_url;
    }

    public void setImagen_url(String imagen_url) {
        this.imagen_url = imagen_url;
    }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Producto{" +
                "id_producto=" + id_producto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio_unitario=" + precio_unitario +
                ", stock=" + stock +
                ", categoria='" + categoria + '\'' +
                ", imagen_url='" + imagen_url + '\'' +
                '}';
    }
}
