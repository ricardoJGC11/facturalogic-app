package com.facturalogic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.facturalogic.config.ConexionDB;
import com.facturalogic.model.Cliente;

public class ClienteRepository {

    // Insertar un nuevo cliente
    public boolean guardar(Cliente cliente) {
        String sql = "INSERT INTO clientes (documento_identidad, nombre_completo, correo) VALUES (?, ?, ?)";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, cliente.getDocumentoIdentidad());
            ps.setString(2, cliente.getNombreCompleto());
            ps.setString(3, cliente.getCorreo());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error al guardar cliente: " + e.getMessage());
            return false;
        }
    }

    // Buscar cliente por su documento de identidad
    public Cliente buscarPorDocumento(String documento) {
        String sql = "SELECT * FROM clientes WHERE documento_identidad = ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, documento);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                        rs.getInt("id"),
                        rs.getString("documento_identidad"),
                        rs.getString("nombre_completo"),
                        rs.getString("correo")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar cliente: " + e.getMessage());
        }
        return null;
    }
}