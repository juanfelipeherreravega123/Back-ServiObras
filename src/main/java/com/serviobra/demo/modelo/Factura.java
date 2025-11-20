package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "facturacion")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_factura;

    private Long id_pedido;
    private Double valor;
    private String metodo_pago;
    private String estado_pago;

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_factura;

    public Long getId_factura() { return id_factura; }
    public void setId_factura(Long id) { this.id_factura = id; }

    public Long getId_pedido() { return id_pedido; }
    public void setId_pedido(Long id) { this.id_pedido = id; }

    public Double getValor() { return valor; }
    public void setValor(Double v) { this.valor = v; }

    public String getMetodo_pago() { return metodo_pago; }
    public void setMetodo_pago(String m) { this.metodo_pago = m; }

    public String getEstado_pago() { return estado_pago; }
    public void setEstado_pago(String e) { this.estado_pago = e; }

    public java.sql.Timestamp getFecha_factura() { return fecha_factura; }
    public void setFecha_factura(java.sql.Timestamp f) { this.fecha_factura = f; }
}
