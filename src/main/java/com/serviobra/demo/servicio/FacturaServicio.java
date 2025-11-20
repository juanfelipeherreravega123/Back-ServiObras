package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Factura;
import com.serviobra.demo.repositorio.FacturaRepositorio;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class FacturaServicio {

    @Autowired
    private FacturaRepositorio repo;

    public Factura crear(Factura f) {
        return repo.save(f);
    }

    public Factura obtener(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Factura generarFactura(Long pedidoId) {
        Factura f = new Factura();
        f.setId_pedido(pedidoId);
        f.setValor(0.0);
        f.setMetodo_pago("pendiente");
        f.setEstado_pago("pendiente");
        f.setFecha_factura(new java.sql.Timestamp(System.currentTimeMillis()));
        return repo.save(f);
    }
}

