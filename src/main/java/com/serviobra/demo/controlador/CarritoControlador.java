package com.serviobra.demo.controlador;

import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.modelo.CarritoItem;
import com.serviobra.demo.modelo.dto.ProductoCantidadRequest;
import com.serviobra.demo.servicio.CarritoServicio;
import com.serviobra.demo.servicio.CotizacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoControlador {

    @Autowired
    private CarritoServicio servicio;

    @Autowired
    private CotizacionServicio cotizacionServicio;  // Servicio de cotización para llevar a carrito

    /**
     * Endpoint para crear un carrito nuevo.
     * 
     * @param c El carrito a crear.
     * @return El carrito creado.
     */
    @PostMapping("/crear")
    public Carrito crear(@RequestBody Carrito c) {
        return servicio.crearCarrito(c);
    }

    /**
     * Endpoint para agregar un producto al carrito.
     * 
     * @param id El ID del carrito.
     * @param request
     * @param productoId El ID del producto que se va a agregar.
     * @param cantidad La cantidad del producto que se va a agregar.
     * @return El carrito actualizado.
     */
    @PostMapping("/{id}/agregar")
    public CarritoItem agregarProducto(
            @PathVariable Long id,               // ID del carrito
            @RequestBody ProductoCantidadRequest request  // Recibir el productoId y la cantidad en el cuerpo de la solicitud
    ) {
        return servicio.agregarProducto(id, request.getProductoId(), request.getCantidad());
    }
    /**
     * Endpoint para obtener los detalles de un carrito por su ID.
     * 
     * @param id El ID del carrito.
     * @return El carrito correspondiente.
     */
    @GetMapping("/{id}")
    public Carrito obtener(@PathVariable Long id) {
        return servicio.obtener(id);
    }

    /**
     * Endpoint para llevar los productos de una cotización a un carrito.
     * 
     * @param id El ID de la cotización.
     * @param usuarioId El ID del usuario que realiza la acción.
     * @return ResponseEntity con código HTTP 200 si la operación fue exitosa.
     */
    @PostMapping("/{id}/llevar-a-carrito")
    public ResponseEntity<Void> llevarACarrito(
            @PathVariable Long id,  // ID de la cotización
            @RequestParam Long usuarioId  // ID del usuario que realizará la acción
    ) {
        try {
            // Llamamos al servicio para llevar los productos de la cotización al carrito
            cotizacionServicio.llevarACarrito(id, usuarioId);
            return ResponseEntity.ok().build();  // Respuesta exitosa
        } catch (Exception e) {
            // En caso de error, respondemos con un código de error
            return ResponseEntity.status(500).build();  // Error en el servidor
        }
    }
}
