package com.serviobra.demo.repositorio;

import com.serviobra.demo.modelo.Sugerencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugerenciaRepositorio extends JpaRepository<Sugerencia, Long> {
}
