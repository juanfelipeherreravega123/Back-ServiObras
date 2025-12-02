package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Cotizacion;
import com.serviobra.demo.servicio.CotizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Map;

@RestController
@RequestMapping("/api/cotizaciones")
@CrossOrigin(origins = "*")
public class CotizacionControlador {

    @Autowired
    private CotizacionServicio servicio;


    /**
     * =====================================================
     *   1. CREAR COTIZACIÓN  (POST /api/cotizaciones)
     * =====================================================
     *
     * El frontend envía:
     * {
     *    "usuarioId": 3,
     *    "carritoId": 10
     * }
     *
     * Y debe devolver:
     * {
     *    "id": 25
     * }
     */
    @PostMapping("")
    public ResponseEntity<?> crearCotizacion(@RequestBody Map<String, Object> body) {
        try {
            Long usuarioId = Long.valueOf(body.get("usuarioId").toString());
            Long carritoId = Long.valueOf(body.get("carritoId").toString());

            Cotizacion cot = new Cotizacion();
            cot.setId_usuario(usuarioId);
            cot.setId_carrito(carritoId);
            cot.setEstado("pendiente");
            cot.setValor_total(0.0);
            cot.setFecha_cotizacion(new Timestamp(System.currentTimeMillis()));

            // Guardar en BD
            Cotizacion guardada = servicio.crear(cot);

            // Devolver JSON correcto al frontend
            return ResponseEntity.ok(Map.of(
                    "id", guardada.getId_cotizacion()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "Error al crear la cotización: " + e.getMessage()
            ));
        }
    }



    /**
     * ==================================================================================
     *   2. PASAR ITEMS DE LA COTIZACIÓN AL CARRITO (POST /api/cotizaciones/{id}/...)
     * ==================================================================================
     *
     * Frontend envía:
     * {
     *    "usuarioId": 3
     * }
     *
     */
    @PostMapping("/{idCotizacion}/llevar-a-carrito")
    public ResponseEntity<?> pasarItemsACarrito(
            @PathVariable Long idCotizacion,
            @RequestBody Map<String, Long> body
    ) {
        try {
            Long usuarioId = body.get("usuarioId");

            servicio.llevarACarrito(idCotizacion, usuarioId);

            return ResponseEntity.ok(Map.of(
                    "mensaje", "Items movidos al carrito correctamente"
            ));

        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                    "error", "No se pudieron mover los items: " + e.getMessage()
            ));
        }
    }
}
