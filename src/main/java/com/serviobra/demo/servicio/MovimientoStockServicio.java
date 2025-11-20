package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.MovimientoStock;
import com.serviobra.demo.repositorio.MovimientoStockRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MovimientoStockServicio {

    @Autowired
    private MovimientoStockRepositorio repo;

    public MovimientoStock registrar(MovimientoStock m) {
        return repo.save(m);
    }
}
