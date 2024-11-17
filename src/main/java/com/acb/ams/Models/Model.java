package com.acb.ams.Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import com.acb.ams.Data.DatabaseConnector;
import com.acb.ams.Views.ViewFactory;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


//Clase model...Intermediaria ?? 
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private DatabaseConnector database;
    //private static DatabaseConnector database;

    //Constructor
    private Model(){
        this.viewFactory = new ViewFactory();
        this.database = new DatabaseConnector();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    /**
     * Método estático para verificar si un usuario existe en la base de datos con
     * un nombre de usuario, contraseña y tipo de cuenta específicos.
     * 
     * @param username    Nombre de usuario.
     * @param password    Contraseña del usuario.
     * @param accountType Tipo de cuenta del usuario (Ejemplo: "Estudiante",
     *                    "Profesor", "Coordinador").
     * @return true si el usuario es válido, false si no lo es o si ocurre algún
     *         error.
     */
    public boolean verifyUser(String username, String password, String accountType) {
        String query = "SELECT U.USU_NOMUSUARIO, U.USU_CONTRASENIA, P.PER_TIPO\n" +
                       "FROM AMS_PERSONAS P \n" +
                       "INNER JOIN AMS_USUARIOS U \n" +
                       "ON P.PER_ID = U.PER_ID\n" +
                       "WHERE U.USU_NOMUSUARIO = ? \n" +
                       "  AND U.USU_CONTRASENIA = ? \n" +
                       "  AND P.PER_TIPO = ?;";

        // Abrir conexión y ejecutar consulta
        try (Connection connection = database.getConnection(); // Usa el método estático getConnection
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Configurar los parámetros de la consulta
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, accountType);

            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Retorna true si existe un registro que coincide
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar el usuario: " + e.getMessage());
            return false; // Retorna false si ocurre algún error
        }
    }

    //Metodo insert
    public void insertPerson(
    String tipoUsuarioComboBox, 
    String identificacionTxt,
    String tipoIdTxt, 
    String nombresTxt, 
    String apellidosTxt, 
    String direccionTxt,
    String barrioTxt, 
    String ciudadTxt, 
    String correoTxt, 
    String celularTxt,
    LocalDate fechaMatriculaPicker, 
    String cursoComboBox, 
    LocalDate fechaContratacionPicker) {

    String insertPersonSQL = "INSERT INTO AMS_PERSONAS (PER_EMAIL, PER_NOMBRES, PER_APELLIDOS, PER_ESTADO, PER_TIPO, CUR_ID) VALUES (?, ?, ?, 'Activo', ?, ?)";
    String insertContactSQL = "INSERT INTO AMS_CONTACTO (CONT_CORREO, CONT_DIRECCION, CONT_CIUDAD, CONT_TELEFONO, PER_ID) VALUES (?, ?, ?, ?, ?)";
    
    Connection conn = null;
    PreparedStatement insertPersonStmt = null;
    PreparedStatement insertContactStmt = null;

    try {
        conn = database.getConnection();
        conn.setAutoCommit(false); // Inicia una transacción

        // Insertar datos en AMS_PERSONAS
        insertPersonStmt = conn.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);
        insertPersonStmt.setString(1, correoTxt);
        insertPersonStmt.setString(2, nombresTxt);
        insertPersonStmt.setString(3, apellidosTxt);
        insertPersonStmt.setString(4, tipoUsuarioComboBox);
        insertPersonStmt.setString(5, "Estudiante".equals(tipoUsuarioComboBox) ? cursoComboBox : null);
        insertPersonStmt.executeUpdate();

        // Obtener el ID generado automáticamente
        ResultSet generatedKeys = insertPersonStmt.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new SQLException("No se pudo obtener el ID generado para la persona.");
        }
        int personId = generatedKeys.getInt(1);

        // Insertar datos en AMS_CONTACTO si no es un administrador
        if (!"Administrador".equals(tipoUsuarioComboBox)) {
            insertContactStmt = conn.prepareStatement(insertContactSQL);
            insertContactStmt.setString(1, correoTxt);
            insertContactStmt.setString(2, direccionTxt);
            insertContactStmt.setString(3, ciudadTxt);
            insertContactStmt.setString(4, celularTxt);
            insertContactStmt.setInt(5, personId);
            insertContactStmt.executeUpdate();
        }

        // Confirmar la transacción
        conn.commit();
        System.out.println("Persona insertada correctamente.");

    } catch (SQLException e) {
        if (conn != null) {
            try {
                conn.rollback(); // Revertir la transacción en caso de error
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        try {
            if (insertPersonStmt != null) insertPersonStmt.close();
            if (insertContactStmt != null) insertContactStmt.close();
            if (conn != null) conn.setAutoCommit(true); // Restaurar el modo por defecto
            if (conn != null) conn.close();
        } catch (SQLException closeEx) {
            closeEx.printStackTrace();
        }
    }
}



    

}
