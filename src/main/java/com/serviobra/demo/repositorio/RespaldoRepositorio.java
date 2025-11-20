package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Respaldo;

public interface RespaldoRepositorio extends JpaRepository<Respaldo, Long> {
}
