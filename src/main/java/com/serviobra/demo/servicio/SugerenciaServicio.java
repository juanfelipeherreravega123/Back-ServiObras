package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Sugerencia;
import com.serviobra.demo.repositorio.SugerenciaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugerenciaServicio {

    private final SugerenciaRepositorio repo;

    public SugerenciaServicio(SugerenciaRepositorio repo) {
        this.repo = repo;
    }

    // GUARDAR
    public Sugerencia guardar(Sugerencia s) {
        return repo.save(s);
    }

    // LISTAR TODAS LAS SUGERENCIAS
    public List<Sugerencia> listar() {
        return repo.findAll();
    }

    // ELIMINAR POR ID
    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
