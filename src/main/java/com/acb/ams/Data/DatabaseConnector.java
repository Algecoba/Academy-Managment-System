package com.acb.ams.Data;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector() {
        Properties properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            // Cargar el archivo de propiedades
            if (input == null) {
                System.out.println("No se pudo encontrar el archivo database.properties");
                return;
            }
            properties.load(input);

            // Leer detalles de conexión
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            // Establecer la conexión
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos");

        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método estático para verificar si un usuario existe en la base de datos con un nombre de usuario, contraseña y tipo de cuenta específicos.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param accountType Tipo de cuenta del usuario (Ejemplo: "Estudiante", "Profesor", "Coordinador").
     * @return true si el usuario es válido, false si no lo es o si ocurre algún error.
     */
    public static boolean verifyUser(String username, String password, String accountType) {
        String query = "SELECT U.USU_NOMUSUARIO, U.USU_CONTRASEÑA, P.PER_TIPO\n" +
                "FROM AMS_PERSONAS P \n" +
                "INNER JOIN AMS_USUARIOS U \n" +
                "ON P.PER_ID = U.PER_ID\n" +
                "WHERE U.USU_NOMUSUARIO = ? \n" +
                "  AND U.USU_CONTRASEÑA = ? \n" +
                "  AND P.PER_TIPO = ?;\n";

        // Abrir conexión y ejecutar consulta
        try (Connection connection = new DatabaseConnector().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Configurar los parámetros de la consulta
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, accountType);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Retorna true si existe un registro que coincide

        } catch (Exception e) {
            System.out.println("Error al verificar usuario en la base de datos");
            e.printStackTrace();
            return false;
        }
    }

    // Método de prueba para verificar la conexión
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();
        Connection connection = dbConnector.getConnection();

        if (connection != null) {
            System.out.println("Conexión establecida y lista para usar.");
        } else {
            System.out.println("No se pudo establecer la conexión.");
        }

        dbConnector.closeConnection();
    }
}
