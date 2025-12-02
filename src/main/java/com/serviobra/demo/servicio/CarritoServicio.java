package com.serviobra.demo.servicio;

import com.serviobra.demo.modelo.Carrito;
import com.serviobra.demo.repositorio.CarritoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.serviobra.demo.modelo.CarritoItem;

import java.util.List;

@Service
public class CarritoServicio {

    @Autowired
    private CarritoRepositorio carritoRepo;

    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepo.save(carrito);
    }

    public List<Carrito> obtenerTodos() {
        return carritoRepo.findAll();
    }

    public Carrito obtenerPorId(Long id) {
        return carritoRepo.findById(id).orElse(null);
    }

    public List<Carrito> obtenerPorUsuario(Long idUsuario) {
        return carritoRepo.findByIdUsuario(idUsuario);
    }

    public Carrito actualizarCarrito(Long id, Carrito datos) {
        Carrito carrito = carritoRepo.findById(id).orElse(null);
        if (carrito == null) return null;

        carrito.setEstado(datos.getEstado());
        carrito.setIdUsuario(datos.getIdUsuario());

        return carritoRepo.save(carrito);
    }

    public void eliminarCarrito(Long id) {
        carritoRepo.deleteById(id);
    }
	public Carrito agregarItem(Long carritoId, CarritoItem nuevoItem) {

    Carrito carrito = carritoRepo.findById(carritoId)
            .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

    // Asignar relación
    nuevoItem.setIdCarrito(carrito.getId_carrito());
    nuevoItem.setCarrito(carrito);

    // Agregar item
    carrito.getItems().add(nuevoItem);

    // Recalcular total
    carrito.calcularTotal();

    return carritoRepo.save(carrito);
}

}
