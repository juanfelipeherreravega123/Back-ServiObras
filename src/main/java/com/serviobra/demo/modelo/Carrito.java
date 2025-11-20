package com.serviobra.demo.modelo;

import jakarta.persistence.*;

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

    public Long getId_carrito() { return id_carrito; }
    public void setId_carrito(Long id) { this.id_carrito = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id) { this.id_usuario = id; }

    public String getEstado() { return estado; }
    public void setEstado(String e) { this.estado = e; }

    public java.sql.Timestamp getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(java.sql.Timestamp f) { this.fecha_creacion = f; }
}
