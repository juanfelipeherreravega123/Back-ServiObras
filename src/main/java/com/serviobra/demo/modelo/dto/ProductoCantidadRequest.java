package com.serviobra.demo.modelo.dto;

public class ProductoCantidadRequest {

    private Long productoId;   // ID del producto a agregar al carrito
    private int cantidad;      // Cantidad de ese producto a agregar

    // Constructor vacío
    public ProductoCantidadRequest() {}

    // Constructor con parámetros
    public ProductoCantidadRequest(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ProductoCantidadRequest{" +
                "productoId=" + productoId +
                ", cantidad=" + cantidad +
                '}';
    }
}
