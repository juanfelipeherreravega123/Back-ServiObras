package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Factura;

public interface FacturaRepositorio extends JpaRepository<Factura, Long> {
}

