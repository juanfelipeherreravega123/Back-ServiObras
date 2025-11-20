package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Producto;
import com.serviobra.demo.repositorio.ProductoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio repo;

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto crear(Producto p) {
        return repo.save(p);
    }

    public Producto obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
