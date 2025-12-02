package com.serviobra.demo.repositorio;

import com.serviobra.demo.modelo.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CarritoRepositorio extends JpaRepository<Carrito, Long> {

    List<Carrito> findByIdUsuario(Long idUsuario);
}
