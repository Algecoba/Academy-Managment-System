package com.acb.ams.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;


import com.acb.ams.Data.DatabaseConnector;
import com.acb.ams.Views.ViewFactory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

//Clase model...Intermediaria ?? 
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private DatabaseConnector database;
    // private static DatabaseConnector database;

    // Constructor
    private Model() {
        this.viewFactory = new ViewFactory();
        this.database = new DatabaseConnector();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
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
        String query = "SELECT U.USU_NOMUSUARIO, U.USU_CONTRASEÑA, P.PER_TIPO\n" +
                "FROM AMS_PERSONAS P \n" +
                "INNER JOIN AMS_USUARIOS U \n" +
                "ON P.PER_ID = U.PER_ID\n" +
                "WHERE U.USU_NOMUSUARIO = ? \n" +
                "  AND U.USU_CONTRASEÑA = ? \n" +
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

    // Metodo insert
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
                if (insertPersonStmt != null)
                    insertPersonStmt.close();
                if (insertContactStmt != null)
                    insertContactStmt.close();
                if (conn != null)
                    conn.setAutoCommit(true); // Restaurar el modo por defecto
                if (conn != null)
                    conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
    }

    public ObservableList<Person> dataForTableActividades() {
        String consulta = "SELECT " +
                "CONCAT(p.per_nombres, ' ', p.per_apellidos) AS Estudiante, " +
                "n.not_definitiva AS Definitiva, " +
                "a.act_nombre AS Actividad " +
                "FROM ams_personas p " +
                "INNER JOIN ams_notas n ON p.per_id = n.est_id " +
                "INNER JOIN ams_asignaturas asi ON n.asig_id = asi.asig_id " +
                "INNER JOIN ams_actividades a ON asi.asig_id = a.asig_id";

        // Abrir conexión y ejecutar consulta
        try (Connection connection = database.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(consulta)) {

            System.out.println("Consulta hecha exitosamente - Tabla actividades");

            ObservableList<Person> tablacalificaciones = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String estudiante = resultSet.getString("Estudiante");
                Double definitiva = resultSet.getObject("Definitiva") != null ? resultSet.getDouble("Definitiva") : 0.0;
                String actividad = resultSet.getString("Actividad");

                Person person = new Person(estudiante, actividad, definitiva);
                tablacalificaciones.add(person);
            }
            return tablacalificaciones;

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            return null;
        }
    }

    public ObservableList<Course> loadCoursesNames() {

        ObservableList<Course> listCourse = FXCollections.observableArrayList();

        String consulta = "Select cur_grado as Curso from ams_cursos";

        try (Connection connetion = database.getConnection();
                Statement statement = connetion.createStatement();
                ResultSet rs = statement.executeQuery(consulta)) {

            while (rs.next()) {
                String nameCourse = rs.getString("Curso");
                Course Courses = new Course(nameCourse);
                listCourse.add(Courses);
            }

            return listCourse;
        } catch (Exception e) {

            return null;
        }

    }

    public ObservableList<Course> getCourseStudent(String nombre) {

        ObservableList<Course> listCourse = FXCollections.observableArrayList();

        String consulta = "SELECT cur_grado AS Curso " +
                "FROM ams_cursos AS cur " +
                "JOIN ams_personas AS per ON cur.cur_id = per.per_id " +
                "WHERE per.per_nombres = ?";

        try (Connection connection = database.getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta)) {

            // Sustituir el parámetro
            statement.setString(1, nombre);

            // Ejecutar la consulta
            try (ResultSet rs = statement.executeQuery()) {

                // Procesar resultados
                while (rs.next()) {
                    String nameCourse = rs.getString("Curso");
                    Course course = new Course(nameCourse);
                    listCourse.add(course);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
        }

        return listCourse;
    }

    public String getCodigo() {
        String codigo = null;
        String consulta = "SELECT per_nombres AS Nombre " +
                          "FROM ams_personas AS per " +
                          "JOIN ams_usuarios AS usu ON per.per_id = usu.per_id " +
                          "ORDER BY usu.USU_FULTIMOINGRESO DESC " +
                          "LIMIT 1";
    
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(consulta);
             ResultSet rs = statement.executeQuery()) {
    
            if (rs.next()) {
                // Obtiene el valor correcto de la columna per_codigo
                codigo = rs.getString("Nombre");
            }
    
        } catch (Exception e) {
            e.printStackTrace(); // Registra el error en la consola para depuración
        }
    
        return codigo; // Devuelve el código o null si no hay registros
    }
    
    public String capitalize(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre; // Devuelve el mismo valor si es null o vacío
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

    public String getTeacherName(String usuario, String asignatura) {
        String profesor = null;
        String consulta = "SELECT PER.PER_NOMBRES as nombre " + // Se agrega el espacio
                "FROM AMS_PERSONAS PER " +
                "JOIN AMS_PROFESORES_ASIGNATURAS PA ON PER.PER_ID = PA.PRO_ID " +
                "JOIN AMS_ASIGNATURAS ASI ON PA.ASIG_ID = ASI.ASIG_ID " +
                "JOIN AMS_ASISTENCIAS ASIS ON ASI.ASIG_ID = ASIS.ASIG_ID " +
                "JOIN AMS_PERSONAS EST ON ASIS.EST_ID = EST.PER_ID " +
                "WHERE ASI.ASIG_NOMBRE = ? AND EST.PER_NOMBRES = ?";
    
        try (Connection connection = database.getConnection(); // Usa el método estático getConnection
             PreparedStatement statement = connection.prepareStatement(consulta)) {
    
            // Configurar los parámetros de la consulta
            statement.setString(1, asignatura);
            statement.setString(2, usuario);
    
            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { // Verificamos si hay resultados
                    profesor = resultSet.getString("nombre"); // Obtener el nombre del profesor
                }
            }
    
        } catch (SQLException e) {
            e.printStackTrace(); // Manejo de excepciones si ocurre algún error en la consulta
        }
    
        return profesor; // Retorna el nombre del profesor o null si no se encuentra
    }

}
