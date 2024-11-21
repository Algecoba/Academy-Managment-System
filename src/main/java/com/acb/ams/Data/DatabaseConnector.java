package com.acb.ams.Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.acb.ams.Models.Activities;
import com.acb.ams.Models.Attendance;
import com.acb.ams.Models.Course;
import com.acb.ams.Models.Criterion;
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
        String nombresTxt,
        String apellidosTxt,
        String estado) {

    String insertPersonSQL = """
        INSERT INTO AMS_PERSONAS (PER_TIPO, PER_CODIGO, PER_NOMBRES, PER_APELLIDOS, PER_ESTADO) 
        VALUES (?, ?, ?, ?, ?);
    """;

    Connection conn = null;
    PreparedStatement insertPersonStmt = null;

    try {
        conn = getConnection();

        if (conn == null) {
            throw new SQLException("No se pudo obtener la conexión a la base de datos.");
        }

        conn.setAutoCommit(false); // Inicia una transacción

        // Preparar la consulta
        insertPersonStmt = conn.prepareStatement(insertPersonSQL, Statement.RETURN_GENERATED_KEYS);

        // Establecer los valores en el PreparedStatement
        insertPersonStmt.setString(1, tipoUsuarioComboBox);
        insertPersonStmt.setString(2, identificacionTxt);
        insertPersonStmt.setString(3, nombresTxt);
        insertPersonStmt.setString(4, apellidosTxt);
        insertPersonStmt.setString(5, estado);

        // Ejecutar la consulta
        int rowsAffected = insertPersonStmt.executeUpdate();

        if (rowsAffected == 0) {
            throw new SQLException("No se insertó ninguna fila en la tabla AMS_PERSONAS.");
        }

        // Obtener el ID generado automáticamente
        try (ResultSet generatedKeys = insertPersonStmt.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int personId = generatedKeys.getInt(1);
                System.out.println("Persona insertada con ID: " + personId);
            } else {
                throw new SQLException("No se pudo obtener el ID generado para la persona.");
            }
        }

        // Confirmar la transacción
        conn.commit();
    } catch (SQLException e) {
        // Revertir la transacción en caso de error
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        // Cerrar recursos
        if (insertPersonStmt != null) {
            try {
                insertPersonStmt.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
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

    // Consultas Para Estudiante
    public ObservableList<Attendance> getAttendancesForTableCourses(String student, String asignatura){
        String consulta = """
            SELECT 
                A.ASIG_NOMBRE AS Asignatura,
                ROUND(SUM(CASE WHEN ASIS.ASIS_ESTADO = 'Presente' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS Porcentaje_Asistencia,
                ROUND(SUM(CASE WHEN ASIS.ASIS_ESTADO = 'Ausente' THEN 1 ELSE 0 END) * 100.0 / COUNT(*), 2) AS Porcentaje_Inasistencia
            FROM 
                AMS_PERSONAS AS P_ESTU
            JOIN 
                AMS_ASISTENCIAS AS ASIS ON P_ESTU.PER_ID = ASIS.EST_ID
            JOIN 
                AMS_ASIGNATURAS AS A ON ASIS.ASIG_ID = A.ASIG_ID
            WHERE 
                P_ESTU.PER_NOMBRES = ?
                AND A.ASIG_NOMBRE = ?
            GROUP BY 
                A.ASIG_NOMBRE;
        """;

        ObservableList<Attendance> attendances = FXCollections.observableArrayList(); 

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(consulta)) {

            // Establecer el parámetro de la consulta
            preparedStatement.setString(1, student);
            preparedStatement.setString(2, asignatura);

            // Ejecutar la consulta
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Consulta hecha exitosamente - Asistencias de un curso");

            // Recorrer los resultados
            while (rs.next()) {
                String asig = rs.getString("Asignatura");
                Subject subject = new Subject(asig);
                Double porcentAsistencia = rs.getDouble("Porcentaje_Asistencia");
                Double porcentInasistencia = rs.getDouble("Porcentaje_Inasistencia");

                Attendance attendance = new Attendance(subject, porcentAsistencia, porcentInasistencia);

                // Agregar la actividad a la lista
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Registrar el error
        }

        return attendances;
    }

    public ObservableList<Activities> getActivitiesForTableDashboard(String nombre) {
        String query = """
                SELECT
                    asig.asig_nombre AS asignatura,
                    act.act_nombre AS actividad,
                    act.act_fecha AS fecha_actividad,
                    act.act_nota AS nota,
                    crit.crit_nombre AS criterio
                FROM ams_personas per
                JOIN ams_cursos cur ON per.cur_id = cur.cur_id
                JOIN ams_pensum pen ON cur.pen_id = pen.pen_id
                JOIN ams_asignaturas asig ON pen.pen_id = asig.pen_id
                JOIN ams_actividades act ON asig.asig_id = act.asig_id
                JOIN ams_criterios crit ON act.crit_id = crit.crit_id
                JOIN ams_notas nots ON act.asig_id = nots.asig_id AND nots.est_id = per.per_id
                WHERE per.per_nombres = ?;
                """;

        ObservableList<Activities> activities = FXCollections.observableArrayList();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Establecer el parámetro de la consulta
            preparedStatement.setString(1, nombre);

            // Ejecutar la consulta
            ResultSet rs = preparedStatement.executeQuery();

            System.out.println("Consulta hecha exitosamente - Estudiantes de un curso");

            // Recorrer los resultados
            while (rs.next()) {
                String asignatura = rs.getString("asignatura");
                String actividad = rs.getString("actividad");
                Date fechaActividad = rs.getDate("fecha_actividad");
                Double nota = rs.getDouble("nota");
                String criterio = rs.getString("criterio");

                Activities acti = new Activities(asignatura, actividad, fechaActividad, nota, criterio);

                // Agregar la actividad a la lista
                activities.add(acti);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Registrar el error
        }

        return activities; // Siempre devuelve la lista, aunque esté vacía
    }

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
        String consulta = """
                    SELECT
                        P_PROF.PER_NOMBRES AS Profesor_Nombre
                    FROM
                        AMS_PERSONAS AS P_ESTU
                    JOIN
                        AMS_NOTAS AS N ON P_ESTU.PER_ID = N.EST_ID
                    JOIN
                        AMS_ASIGNATURAS AS A ON N.ASIG_ID = A.ASIG_ID
                    JOIN
                        AMS_PROFESORES_ASIGNATURAS AS PA ON A.ASIG_ID = PA.ASIG_ID
                    JOIN
                        AMS_PERSONAS AS P_PROF ON PA.PRO_ID = P_PROF.PER_ID
                    WHERE
                        P_ESTU.PER_NOMBRES = ?
                        AND A.ASIG_NOMBRE = ?;
                """;

        try (Connection connection = getConnection(); // Usa el método estático getConnection
                PreparedStatement statement = connection.prepareStatement(consulta)) {

            // Configurar los parámetros de la consulta
            statement.setString(1, usuario);
            statement.setString(2, asignatura);

            // Ejecutar la consulta
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { // Verificamos si hay resultados
                    profesor = resultSet.getString("Profesor_Nombre"); // Obtener el nombre del profesor
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

        ObservableList<Subject> criterions = FXCollections.observableArrayList();

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
                criterions.add(subject);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Registrar el error
        }

        return criterions; // Siempre devuelve la lista, aunque esté vacía
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
        String consulta = "SELECT per.per_id AS ID " +
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
                SELECT PE.PER_ID AS ID, PE.PER_NOMBRES AS NOMBRES,
                PE.PER_APELLIDOS AS APELLIDOS, PE.PER_CODIGO AS CODIGO, CUR.CUR_ID AS CURSO
                FROM AMS_PERSONAS AS PE
                JOIN AMS_CURSOS AS CUR ON PE.CUR_ID = CUR.CUR_ID
                JOIN AMS_PENSUM AS PEN ON PEN.PEN_ID = CUR.PEN_ID
                JOIN AMS_ASIGNATURAS AS ASI ON ASI.PEN_ID = PEN.PEN_ID
                JOIN AMS_PROFESORES_ASIGNATURAS AS PAS ON PAS.ASIG_ID = ASI.ASIG_ID
                WHERE PAS.PRO_ID = ?
                AND PAS.ASIG_ID = ?
                """;

        ObservableList<Person> students = FXCollections.observableArrayList();

        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Configurar los parámetros de la consulta
            preparedStatement.setString(1, profe); // Asume que el ID del profesor es String
            preparedStatement.setInt(2, asign.getId()); // Convierte a entero si es necesario

            // Ejecutar la consulta
            ResultSet rs = preparedStatement.executeQuery();
            System.out.println("Consulta realizada exitosamente: Estudiantes de un curso.");

            // Procesar los resultados
            while (rs.next()) {
                String nombres = rs.getString("NOMBRES");
                String apellidos = rs.getString("APELLIDOS");
                int ID = rs.getInt("ID");
                int cursoID = rs.getInt("CURSO");

                // Crear y agregar un objeto Person a la lista
                Person student = new Person(nombres, apellidos, ID, cursoID);
                students.add(student);
            }

        } catch (NumberFormatException e) {
            System.err.println("Error al convertir el ID de asignatura a número: " + asign.getId());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta SQL.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado.");
            e.printStackTrace();
        }

        return students; // Devuelve la lista (puede estar vacía)
    }

    public String capitalize(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre; // Devuelve el mismo valor si es null o vacío
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

}
