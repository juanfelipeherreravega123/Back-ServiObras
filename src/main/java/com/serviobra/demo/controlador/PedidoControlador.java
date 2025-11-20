package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Pedido;
import com.serviobra.demo.servicio.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoControlador {

    @Autowired
    private PedidoServicio servicio;

    @GetMapping
    public List<Pedido> listar() {
        return servicio.listar();
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido p) {
        return servicio.crear(p);
    }
}
