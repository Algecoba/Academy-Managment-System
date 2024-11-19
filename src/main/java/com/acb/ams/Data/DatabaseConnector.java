package com.acb.ams.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {

    private  String username = "root"; // Tu usuario de base de datos
    private  String password = "1234"; // Tu contraseña de base de datos
    private  String url = "jdbc:mysql://localhost:3306/AMS_DB"; // Corrige 'locallhost' a 'localhost'
    
    // Método estático para obtener la conexión
    public  Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            throw e; // Lanza la excepción para que quien llame a este método la maneje
        }
        return connection;
    }

    // Método estático para cerrar la conexión
    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    

    
}
