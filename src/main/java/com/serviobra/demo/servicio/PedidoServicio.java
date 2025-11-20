package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Pedido;
import com.serviobra.demo.repositorio.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio repo;

    public Pedido crear(Pedido p) {
        return repo.save(p);
    }

    public List<Pedido> listar() {
        return repo.findAll();
    }
}
