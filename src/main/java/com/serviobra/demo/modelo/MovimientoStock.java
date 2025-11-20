package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "movimientos_stock")
public class MovimientoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_movimiento;

    private Long id_producto;
    private Integer cantidad;
    private String tipo_movimiento; // entrada / salida

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_movimiento;

    public Long getId_movimiento() { return id_movimiento; }
    public void setId_movimiento(Long id) { this.id_movimiento = id; }

    public Long getId_producto() { return id_producto; }
    public void setId_producto(Long id) { this.id_producto = id; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer c) { this.cantidad = c; }

    public String getTipo_movimiento() { return tipo_movimiento; }
    public void setTipo_movimiento(String t) { this.tipo_movimiento = t; }

    public java.sql.Timestamp getFecha_movimiento() { return fecha_movimiento; }
    public void setFecha_movimiento(java.sql.Timestamp f) { this.fecha_movimiento = f; }
}
