package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Factura;
import com.serviobra.demo.servicio.FacturaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturacion")
@CrossOrigin(origins = "*")
public class FacturacionControlador {

    @Autowired
    private FacturaServicio facturaServicio;

    @PostMapping("/generar/{pedidoId}")
    public Factura generarFactura(@PathVariable Long pedidoId) {
        return facturaServicio.generarFactura(pedidoId);
    }
}
