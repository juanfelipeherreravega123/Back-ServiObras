package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Respaldo;
import com.serviobra.demo.repositorio.RespaldoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespaldoServicio {

    @Autowired
    private RespaldoRepositorio repo;

    public Respaldo crear(Respaldo r) {
        return repo.save(r);
    }
}
