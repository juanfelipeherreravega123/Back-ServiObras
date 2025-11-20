package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Factura;
import com.serviobra.demo.servicio.FacturaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/facturas")
@CrossOrigin(origins = "*")
public class FacturaControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @PostMapping("/{pedidoId}")
    public Factura generarFactura(@PathVariable Long pedidoId) {
        return facturaServicio.generarFactura(pedidoId);
    }
}

