package com.serviobra.demo.modelo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cotizacion;

    @JsonProperty("id_usuario")
    @Column(name = "id_usuario")
    private Long id_usuario;

    @JsonProperty("id_carrito")
    @Column(name = "id_carrito", nullable = false)
    private Long id_carrito;

    @JsonProperty("valor_total")
    @Column(name = "valor_total")
    private Double valor_total;

    @Column(name = "estado")
    private String estado;

    @JsonProperty("fecha_cotizacion")
    @Column(name = "fecha_cotizacion")
    private java.sql.Timestamp fecha_cotizacion;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL)
    private List<CarritoItem> items;


    public void calcularTotal() {
        if (items != null) {
            this.valor_total = items.stream()
                .mapToDouble(CarritoItem::getSubtotal)
                .sum();
        }
    }

    // getters y setters
    public Long getId_cotizacion() { return id_cotizacion; }
    public void setId_cotizacion(Long id) { this.id_cotizacion = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id_usuario) { this.id_usuario = id_usuario; }

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id_carrito) { this.id_carrito = id_carrito; }

    public Double getValor_total() { return valor_total; }
    public void setValor_total(Double valor_total) { this.valor_total = valor_total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public java.sql.Timestamp getFecha_cotizacion() { return fecha_cotizacion; }
    public void setFecha_cotizacion(java.sql.Timestamp fecha) { this.fecha_cotizacion = fecha; }

    public List<CarritoItem> getItems() { return items; }
    public void setItems(List<CarritoItem> items) { this.items = items; }
}
