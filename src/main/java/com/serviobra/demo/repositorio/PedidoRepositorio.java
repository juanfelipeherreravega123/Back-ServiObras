package com.serviobra.demo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import com.serviobra.demo.modelo.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
}
