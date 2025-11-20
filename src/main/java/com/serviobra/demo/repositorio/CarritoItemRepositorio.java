package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.CarritoItem;

public interface CarritoItemRepositorio extends JpaRepository<CarritoItem, Long> {
}
