package com.acb.ams.Controllers.Student;

import com.acb.ams.Models.Course;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Text username;

    @FXML
    private Label average_P1;

    @FXML
    private Label average_P2;

    @FXML
    private Label average_P3;

    @FXML
    private Label average_P4;

    @FXML
    private Label loginDate;

    @FXML
    private ListView<Course> CoursesListView;

    // Inicialización de la lista de cursos
    private List<Course> courses;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar el nombre de usuario, puedes obtenerlo de la sesión o base de datos
        username.setText("Hola, Alejandro");  // Cambiar según el usuario

        // Inicializar la lista de cursos
        courses = getCourses(); // Aquí deberías obtener los cursos de la base de datos o de una simulación

        // Si no hay cursos, no agregar nada al ListView
        if (courses != null && !courses.isEmpty()) {
            CoursesListView.getItems().addAll(courses);
        }

        // Mostrar los promedios en las etiquetas correspondientes
        // Para efectos de prueba, se agregan promedios simulados
        average_P1.setText("4.5");
        average_P2.setText("4.6");
        average_P3.setText("4.8");
        average_P4.setText("5.0");

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
