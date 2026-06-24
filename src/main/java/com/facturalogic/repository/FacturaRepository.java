package com.facturalogic.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import com.facturalogic.config.ConexionDB;
import com.facturalogic.model.Factura;
import com.facturalogic.model.DetalleFactura;

public class FacturaRepository {

    public boolean guardar(Factura factura) {
        String sqlFactura = "INSERT INTO facturas (numero_factura, cliente_id, total) VALUES (?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalles_factura (factura_id, producto_id, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        Connection con = null;
        
        try {
            con = ConexionDB.getConexion();
            // DESACTIVAMOS el autocommit para controlar la transacción manualmente
            con.setAutoCommit(false);
            
            // 1. Insertar la cabecera de la Factura y recuperar el ID auto-generado
            try (PreparedStatement psFactura = con.prepareStatement(sqlFactura, Statement.RETURN_GENERATED_KEYS)) {
                psFactura.setString(1, factura.getNumeroFactura());
                psFactura.setInt(2, factura.getCliente().getId());
                psFactura.setDouble(3, factura.getTotal());
                
                psFactura.executeUpdate();
                
                // Obtenemos el ID asignado por MySQL a esta factura
                int facturaIdGenerated = -1;
                try (ResultSet generatedKeys = psFactura.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        facturaIdGenerated = generatedKeys.getInt(1);
                    }
                }
                
                if (facturaIdGenerated == -1) {
                    throw new SQLException("No se pudo obtener el ID de la factura generada.");
                }

                // 2. Insertar todos los detalles vinculados a ese ID generado
                try (PreparedStatement psDetalle = con.prepareStatement(sqlDetalle)) {
                    for (DetalleFactura detalle : factura.getDetalles()) {
                        psDetalle.setInt(1, facturaIdGenerated);
                        psDetalle.setInt(2, detalle.getProducto().getId());
                        psDetalle.setInt(3, detalle.getCantidad());
                        psDetalle.setDouble(4, detalle.getPrecioUnitario());
                        psDetalle.setDouble(5, detalle.getSubtotal());
                        
                        psDetalle.addBatch(); // Los acumula para enviarlos juntos en bloque (eficiencia)
                    }
                    psDetalle.executeBatch(); // Ejecuta todos los detalles de un solo golpe
                }
            }
            
            // Si todo salió perfecto, confirmamos la operación en el disco duro
            con.commit();
            return true;
            
        } catch (SQLException e) {
            System.err.println("Error en la transacción de la factura: " + e.getMessage());
            if (con != null) {
                try {
                    System.out.println("Aplicando Rollback... Operación cancelada de forma segura.");
                    con.rollback(); // Deshace todo si hubo un error técnico
                } catch (SQLException ex) {
                    System.err.println("Error al aplicar rollback: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            // Aseguramos el cierre físico de la conexión
            if (con != null) {
                try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        }
    }
}