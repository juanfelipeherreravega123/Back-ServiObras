package com.serviobra.demo.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cotizacion;

    private Long id_usuario;
    private Long id_carrito;
    private Double valor_total;
    private String estado;

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_cotizacion;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL)
    private List<CarritoItem> items; // Lista de items en la cotización

    // Método para calcular el total basado en los items
    public void calcularTotal() {
        this.valor_total = items.stream()
                               .mapToDouble(item -> item.getSubtotal())
                               .sum();
    }

    // Getters y setters
    public Long getId_cotizacion() { return id_cotizacion; }
    public void setId_cotizacion(Long id) { this.id_cotizacion = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id) { this.id_usuario = id; }

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id) { this.id_carrito = id; }

    public Double getValor_total() { return valor_total; }
    public void setValor_total(Double v) { this.valor_total = v; }

    public String getEstado() { return estado; }
    public void setEstado(String e) { this.estado = e; }

    public java.sql.Timestamp getFecha_cotizacion() { return fecha_cotizacion; }
    public void setFecha_cotizacion(java.sql.Timestamp f) { this.fecha_cotizacion = f; }

    public List<CarritoItem> getItems() { return items; }
    public void setItems(List<CarritoItem> items) { this.items = items; }
}

