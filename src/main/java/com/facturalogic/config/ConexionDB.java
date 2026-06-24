package com.facturalogic.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    
    // Configuración de los parámetros de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/factura_db?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "admin1234"; // <-- Coloca aquí la contraseña que configuraste

    // Método estático para obtener la conexión
    public static Connection getConexion() {
        Connection conexion = null;
        try {
            // Registrar el driver de MySQL (opcional en versiones modernas pero buena práctica)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentar establecer el enlace
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el conector de MySQL. " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return conexion;
    }
}