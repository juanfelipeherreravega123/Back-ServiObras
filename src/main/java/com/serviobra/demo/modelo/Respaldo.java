package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "respaldo_datos")
public class Respaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_respaldo;

    private String tabla;
    private String datos_json;

    @Column(columnDefinition = "TIMESTAMP")
    private java.sql.Timestamp fecha_respaldo;

    public Long getId_respaldo() { return id_respaldo; }
    public void setId_respaldo(Long id) { this.id_respaldo = id; }

    public String getTabla() { return tabla; }
    public void setTabla(String t) { this.tabla = t; }

    public String getDatos_json() { return datos_json; }
    public void setDatos_json(String d) { this.datos_json = d; }

    public java.sql.Timestamp getFecha_respaldo() { return fecha_respaldo; }
    public void setFecha_respaldo(java.sql.Timestamp f) { this.fecha_respaldo = f; }
}
