package com.serviobra.demo.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_carrito;

    private Long id_usuario;
    private String estado;

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_creacion;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
    private List<CarritoItem> items;  // Relación con CarritoItem

    private Double total;

    // Método para agregar un item al carrito
    public void agregarItem(CarritoItem item) {
        items.add(item);
        calcularTotal();
    }

    // Método para calcular el total del carrito
    public void calcularTotal() {
        this.total = items.stream()
                          .mapToDouble(item -> item.getSubtotal())
                          .sum();
    }

    // Getters y setters
    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id) { this.id_carrito = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id) { this.id_usuario = id; }

    public String getEstado() { return estado; }
    public void setEstado(String e) { this.estado = e; }

    public java.sql.Timestamp getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(java.sql.Timestamp f) { this.fecha_creacion = f; }

    public List<CarritoItem> getItems() { return items; }
    public void setItems(List<CarritoItem> items) { this.items = items; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}


