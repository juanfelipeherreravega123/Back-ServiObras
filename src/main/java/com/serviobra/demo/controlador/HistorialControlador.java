package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Respaldo;
import com.serviobra.demo.repositorio.RespaldoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historial")
public class HistorialControlador {

    @Autowired
    private RespaldoRepositorio respaldoRepositorio;

    @GetMapping
    public List<Respaldo> obtenerHistorial() {
        return respaldoRepositorio.findAll();
    }
}
