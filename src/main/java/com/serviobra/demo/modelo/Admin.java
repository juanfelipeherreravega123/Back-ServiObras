package com.serviobra.demo.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_admin;

    private Long id_usuario;
    private String permisos;

    public Long getId_admin() { return id_admin; }
    public void setId_admin(Long id) { this.id_admin = id; }

    public Long getId_usuario() { return id_usuario; }
    public void setId_usuario(Long id) { this.id_usuario = id; }

    public String getPermisos() { return permisos; }
    public void setPermisos(String p) { this.permisos = p; }
}
