package com.facturalogic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.facturalogic.config.ConexionDB;
import com.facturalogic.model.Producto;

public class ProductoRepository {

    // 1. Guardar o Insertar un Producto en la base de datos
    public boolean guardar(Producto producto) {
        String sql = "INSERT INTO productos (codigo_barras, nombre, precio_venta, stock) VALUES (?, ?, ?, ?)";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            // Inyectamos los valores del objeto Java en los '?' del SQL
            ps.setString(1, producto.getCodigoBarras());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getStock());
            
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // Si insertó al menos una fila, devuelve true
            
        } catch (SQLException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
            return false;
        }
    }

    // 2. Buscar un Producto por su Código de Barras (Clave analítica para las ventas)
    public Producto buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo_barras = ?";
        
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, codigo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Si la base de datos encontró el registro, armamos el objeto Java
                    return new Producto(
                        rs.getInt("id"),
                        rs.getString("codigo_barras"),
                        rs.getString("nombre"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar producto por código: " + e.getMessage());
        }
        return null; // Si no lo encuentra o hay error, devuelve un vacío controlado
    }
}