package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.MovimientoStock;

public interface MovimientoStockRepositorio extends JpaRepository<MovimientoStock, Long> {
}
