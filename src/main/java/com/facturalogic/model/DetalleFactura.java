package com.facturalogic.model;

public class DetalleFactura {
    private int id;
    private int facturaId;
    private Producto producto; // Vinculación directa al objeto Producto
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleFactura() {}

    public DetalleFactura(int id, int facturaId, Producto producto, int cantidad, double precioUnitario, double subtotal) {
        this.id = id;
        this.facturaId = facturaId;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFacturaId() { return facturaId; }
    public void setFacturaId(int facturaId) { this.facturaId = facturaId; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}