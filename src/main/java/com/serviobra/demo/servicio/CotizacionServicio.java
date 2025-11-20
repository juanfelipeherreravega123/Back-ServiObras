package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Cotizacion;
import com.serviobra.demo.repositorio.CotizacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotizacionServicio {

    @Autowired
    private CotizacionRepositorio repo;

    public List<Cotizacion> listar() {
        return repo.findAll();
    }

    public Cotizacion crear(Cotizacion c) {
        return repo.save(c);
    }
}
