package com.acb.ams.Data;

import java.sql.*;
import java.sql.DriverManager;

public class Database {

    
    private static final String URL = "jdbc:mysql://localhost:3306/db_ams?useSSL=false&serverTimezone=UTC";
    private static final String USER = "Admin";
    private static final String PASSWORD = "@MSusb24";
    public static Connection connection;

    // Método para obtener la conexión con la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos");
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para verificar el usuario, la contraseña y el tipo de cuenta
    public static boolean verifyUser(String username, String password, String accountType) {
        // Verificar que la conexión esté establecida
        if (connection == null) {
            connection = getConnection();
        }

        // Consulta SQL para verificar usuario, contraseña y tipo de cuenta en la base de datos
        String query = "SELECT USU.USU_CONTRASEÑA, PER.PER_TIPO " +
                       "FROM AMS_USUARIOS USU " +
                       "INNER JOIN AMS_PERSONAS PER ON USU.PER_ID = PER.PER_ID " +
                       "WHERE USU.USU_NOMUSUARIO = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("USU_CONTRASEÑA");
                    String storedRole = resultSet.getString("PER_TIPO");

                    // Verificar contraseña y rol
                    if (storedPassword.equals(password) && storedRole.equalsIgnoreCase(accountType)) {
                        return true;
                    } else {
                        System.out.println("Credenciales o rol incorrecto.");
                    }
                } else {
                    System.out.println("Usuario no encontrado.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
