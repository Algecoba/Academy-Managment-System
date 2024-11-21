package com.acb.ams.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.acb.ams.Models.Course;
import com.acb.ams.Models.Person;
import com.acb.ams.Models.Qualification;
import com.acb.ams.Models.Subject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseConnector {

    private String username = "root"; // Tu usuario de base de datos
    private String password = "1234"; // Tu contraseña de base de datos
    private String url = "jdbc:mysql://localhost:3306/AMS_DB"; // Corrige 'locallhost' a 'localhost'

    // Método estático para obtener la conexión
    public Connection getConnection() throws SQLException {
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
    // Consultas para Login
    public boolean verifyUser(String username, String password, String accountType) {
        String query = "SELECT U.USU_NOMUSUARIO, U.USU_CONTRASENIA, P.PER_TIPO\n" +
                "FROM AMS_PERSONAS P \n" +
                "INNER JOIN AMS_USUARIOS U \n" +
                "ON P.PER_ID = U.PER_ID\n" +
                "WHERE U.USU_NOMUSUARIO = ? \n" +
                "  AND U.USU_CONTRASENIA = ? \n" +
                "  AND P.PER_TIPO = ?;";

        // Abrir conexión y ejecutar consulta
        try (Connection connection = getConnection(); // Usa el método estático getConnection
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

    // Consultas para Admin
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
            conn = getConnection();
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

    // Consultas para Profesores
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
        try (Connection connection = getConnection();
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

        try (Connection connetion = getConnection();
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

    // Consultas Para Estudiantes
    public ObservableList<Course> getCourseStudent(String nombre) {

        ObservableList<Course> listCourse = FXCollections.observableArrayList();

        String consulta = "SELECT cur_grado AS Curso " +
                "FROM ams_cursos AS cur " +
                "JOIN ams_personas AS per ON cur.cur_id = per.per_id " +
                "WHERE per.per_nombres = ?";

        try (Connection connection = getConnection();
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

    public String getTeacherName(String usuario, String asignatura) {
        String profesor = null;
        String consulta = "SELECT PER.PER_NOMBRES as nombre " + // Se agrega el espacio
                "FROM AMS_PERSONAS PER " +
                "JOIN AMS_PROFESORES_ASIGNATURAS PA ON PER.PER_ID = PA.PRO_ID " +
                "JOIN AMS_ASIGNATURAS ASI ON PA.ASIG_ID = ASI.ASIG_ID " +
                "JOIN AMS_ASISTENCIAS ASIS ON ASI.ASIG_ID = ASIS.ASIG_ID " +
                "JOIN AMS_PERSONAS EST ON ASIS.EST_ID = EST.PER_ID " +
                "WHERE ASI.ASIG_NOMBRE = ? AND EST.PER_NOMBRES = ?";

        try (Connection connection = getConnection(); // Usa el método estático getConnection
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

    public ObservableList<Subject> getSubjectStudent(String nombreEstudiante) {
        String query = """
                SELECT asig.asig_nombre AS nombre
                FROM ams_personas per
                JOIN ams_cursos cur ON per.cur_id = cur.cur_id
                JOIN ams_pensum pen ON cur.pen_id = pen.pen_id
                JOIN ams_asignaturas asig ON pen.pen_id = asig.asig_id
                WHERE per.per_nombres = ?
                """;

        ObservableList<Subject> listSubject = FXCollections.observableArrayList();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Establecer el parámetro de la consulta
            preparedStatement.setString(1, nombreEstudiante);

            // Ejecutar la consulta
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Consulta hecha exitosamente - Tabla asignaturas");

            // Recorrer los resultados
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                Subject subject = new Subject(nombre); // Constructor correcto
                listSubject.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
        }

        return listSubject; // Siempre devuelve la lista, aunque esté vacía
    }

    public Qualification[] getAveranges(String studentName) {
        // Usar ArrayList para un tamaño dinámico
        List<Qualification> qualificationsList = new ArrayList<>();

        String consulta = "SELECT AVG(notas.not_definitiva) AS Promedio " +
                "FROM ams_personas est " +
                "JOIN ams_notas notas ON est.per_id = notas.est_id " +
                "JOIN ams_cortes cor ON notas.cor_id = cor.cor_id " +
                "WHERE est.per_nombres = ? " +
                "GROUP BY cor.cor_numcorte";

        // Llena las calificaciones reales desde la base de datos
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(consulta)) {

            stmt.setString(1, studentName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                double promedio = rs.getDouble("Promedio");
                qualificationsList.add(new Qualification(promedio));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convertir la lista a un array si es necesario
        // Limitar el tamaño a 3 si es necesario (o mantener la longitud del resultado
        // real)
        Qualification[] qualifications = new Qualification[4];
        for (int i = 0; i < 4; i++) {
            qualifications[i] = (i < qualificationsList.size()) ? qualificationsList.get(i)
                    : (new Qualification(0.0));
        }

        // Si hay menos de 3 cortes, los valores restantes permanecen en su valor
        // predeterminado
        return qualifications;
    }

    // Consultas de Uso Global
    public String getName() {
        String codigo = null;
        String consulta = "SELECT per_nombres AS Nombre " +
                "FROM ams_personas AS per " +
                "JOIN ams_usuarios AS usu ON per.per_id = usu.per_id " +
                "ORDER BY usu.USU_FULTIMOINGRESO DESC " +
                "LIMIT 1";

        try (Connection connection = getConnection();
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

    /*
     * Devuelve el ID del ultimo usuario que ingresó
     */
    public String getLastUserID() {
        String ID = null;
        String consulta = "SELECT per_id AS ID " +
                "FROM ams_personas AS per " +
                "JOIN ams_usuarios AS usu ON per.per_id = usu.per_id " +
                "ORDER BY usu.USU_FULTIMOINGRESO DESC " +
                "LIMIT 1";

        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(consulta);
                ResultSet rs = statement.executeQuery()) {

            if (rs.next()) {
                ID = rs.getString("ID");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ID;
    }

    public ObservableList<Person> getEstudiantesAsignaturaProfesor(Subject asign, String profe) {
        String query = """
                SELECT PER_ID AS ID, PER_NOMBRES AS NOMBRES,
                PER_APELLIDOS AS APELLIDOS, PER_CODIGO AS CODIGO, CUR_ID AS CURSO
                FROM AMS_PERSONAS AS PE
                JOIN AMS_CURSOS AS CUR ON PE.CUR_ID = CUR.CUR_ID
                JOIN AMS_PENSUM AS PEN ON PEN.PEN_ID = CUR.PEN_ID
                JOIN AMS_ASIGNATURAS AS ASI ON ASI.PEN_ID = PEN.PEN_ID
                JOIN AMS_PROFESORES_ASIGNATURAS AS PAS ON PAS.ASIG_ID == ASI.ASIG_ID
                WHERE PAS.PRO_ID = ?
                AND PAS.ASIG_ID = ?
                """;

        ObservableList<Person> listSubject = FXCollections.observableArrayList();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            // Establecer el parámetro de la consulta
            preparedStatement.setString(1, profe);
            preparedStatement.setString(2, asign.getId() + "");
            // Ejecutar la consulta
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Consulta hecha exitosamente - Estudiantes de un curso");

            // Recorrer los resultados
            while (rs.next()) {
                String nombres = rs.getString("NOMBRES");
                String apellidos = rs.getString("APELLIDOS");
                Integer ID = rs.getInt("ID");
                Integer cursoID = rs.getInt("CURSO");
                Person subject = new Person(nombres, apellidos, ID, cursoID); // Constructor correcto
                listSubject.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
        }

        return listSubject; // Siempre devuelve la lista, aunque esté vacía
    }

    public String capitalize(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre; // Devuelve el mismo valor si es null o vacío
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

}
