package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    private Long id_usuario;
    private Long id_cotizacion;
    private String estado;
    private Double valor_final;

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_pedido;

    public Long getId_pedido() { return id_pedido; }
    public void setId_pedido(Long id) { this.id_pedido = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id) { this.id_usuario = id; }

    public Long getId_cotizacion() { return id_cotizacion; }
    public void setId_cotizacion(Long id) { this.id_cotizacion = id; }

    public String getEstado() { return estado; }
    public void setEstado(String e) { this.estado = e; }

    public Double getValor_final() { return valor_final; }
    public void setValor_final(Double v) { this.valor_final = v; }

    public java.sql.Timestamp getFecha_pedido() { return fecha_pedido; }
    public void setFecha_pedido(java.sql.Timestamp f) { this.fecha_pedido = f; }
}
