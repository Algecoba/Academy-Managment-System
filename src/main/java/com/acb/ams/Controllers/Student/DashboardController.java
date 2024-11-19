package com.acb.ams.Controllers.Student;

import com.acb.ams.Models.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Text saludoNombreTxt;

    //Mostrar los promedios de cada periodo del estudiante
    public Label promedio1lbl;
    public Label promedio2lbl;
    public Label promedio3lbl;
    public Label promedio4lbl;

    //Visualización de las ultimas actividades realizadas por el estudiante
    //En todas sus asignaturas
    public TableColumn colAsignatura;
    public TableColumn colActividad;
    public TableColumn colFecha;
    public TableColumn colNota;

    @FXML
    private Label loginDate;

    @FXML
    private ListView<Course> CoursesListView;

    // Inicialización de la lista de cursos
    private List<Course> courses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar el nombre de usuario, puedes obtenerlo de la sesión o base de datos
        saludoNombreTxt.setText("Hola, Alejandro");  // Cambiar según el usuario

        // Inicializar la lista de cursos
        courses = getCourses(); // Aquí deberías obtener los cursos de la base de datos o de una simulación

        // Si no hay cursos, no agregar nada al ListView
        if (courses != null && !courses.isEmpty()) {
            CoursesListView.getItems().addAll(courses);
        }

        // Mostrar los promedios en las etiquetas correspondientes
        // Para efectos de prueba, se agregan promedios simulados
        promedio1lbl.setText("4.5");
        promedio1lbl.setText("4.6");
        promedio1lbl.setText("4.8");
        promedio1lbl.setText("5.0");

        // Mostrar la fecha actual
        LocalDate currentDate = LocalDate.now();
        loginDate.setText("Hoy, " + currentDate.toString());
    }

    // Método simulado para obtener cursos
    private List<Course> getCourses() {
        // Aquí deberías obtener la lista de cursos de la base de datos o algún servicio
        return List.of(); // Retorna una lista vacía para simular que no hay cursos
    }
}
