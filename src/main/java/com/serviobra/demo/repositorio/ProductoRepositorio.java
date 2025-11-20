package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Long> {
}
