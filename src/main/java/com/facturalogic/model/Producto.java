package com.facturalogic.model;

public class Producto {
    // Atributos privados (Encapsulamiento) que mapean la tabla 'productos'
    private int id;
    private String codigoBarras;
    private String nombre;
    private double precioVenta;
    private int stock;

    // Constructor vacío (Requerido por estándar)
    public Producto() {}

    // Constructor completo para inicializar datos fácilmente
    public Producto(int id, String codigoBarras, String nombre, double precioVenta, int stock) {
        this.id = id;
        this.codigoBarras = codigoBarras;
        this.nombre = nombre;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    // Getters y Setters (Para acceder y modificar los datos de forma segura)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigoBarras() { return codigoBarras; }
    public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    // Método para imprimir el objeto de forma legible en consola
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", codigo='" + codigoBarras + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=$" + precioVenta +
                ", stock=" + stock +
                '}';
    }
}