package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Cotizacion;

public interface CotizacionRepositorio extends JpaRepository<Cotizacion, Long> {
}
