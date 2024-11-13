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

    // Simulación de obtener los cursos y promedios (puedes reemplazarlo con datos reales)
    private List<Course> courses;

    //private double[] averages = {4.5, 4.6, 4.8, 5.0}; // Simulación de promedios

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar el nombre de usuario, puedes obtenerlo de la sesión o base de datos
        username.setText("Hola, Alejandro");  // Cambiar según el usuario

        // Mostrar los cursos en el ListView
        CoursesListView.getItems().addAll(courses);

        // Mostrar los promedios en las etiquetas correspondientes
        //average_P1.setText(String.format("%.1f", averages[0]));
        //average_P2.setText(String.format("%.1f", averages[1]));
        //average_P3.setText(String.format("%.1f", averages[2]));
        //average_P4.setText(String.format("%.1f", averages[3]));

        // Mostrar la fecha actual
        LocalDate currentDate = LocalDate.now();
        loginDate.setText("Hoy, " + currentDate.toString());
    }
}
