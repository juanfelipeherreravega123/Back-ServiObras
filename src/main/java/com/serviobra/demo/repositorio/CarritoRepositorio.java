package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Carrito;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {
}
