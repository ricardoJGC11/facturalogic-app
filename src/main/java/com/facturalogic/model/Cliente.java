package com.facturalogic.model;

public class Cliente {
    private int id;
    private String documentoIdentidad;
    private String nombreCompleto;
    private String correo;

    // Constructor vacío
    public Cliente() {}

    // Constructor completo
    public Cliente(int id, String documentoIdentidad, String nombreCompleto, String correo) {
        this.id = id;
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String documentoIdentidad) { this.documentoIdentidad = documentoIdentidad; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", documento='" + documentoIdentidad + '\'' +
                ", nombre='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}