package com.facturalogic.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private int id;
    private String numeroFactura;
    private LocalDateTime fechaEmision;
    private Cliente cliente; // Guardamos el objeto Cliente completo, no solo el ID
    private double total;
    
    // El corazón relacional en Java: Una factura tiene muchos detalles
    private List<DetalleFactura> detalles = new ArrayList<>();

    public Factura() {}

    public Factura(int id, String numeroFactura, LocalDateTime fechaEmision, Cliente cliente, double total) {
        this.id = id;
        this.numeroFactura = numeroFactura;
        this.fechaEmision = fechaEmision;
        this.cliente = cliente;
        this.total = total;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String numeroFactura) { this.numeroFactura = numeroFactura; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public List<DetalleFactura> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleFactura> detalles) { this.detalles = detalles; }
    
    // Método analítico para añadir detalles dinámicamente
    public void agregarDetalle(DetalleFactura detalle) {
        this.detalles.add(detalle);
    }
}