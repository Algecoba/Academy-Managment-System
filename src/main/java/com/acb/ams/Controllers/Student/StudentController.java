package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    @FXML
    private BorderPane borderPane; // El BorderPane principal.

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("StudentController inicializado");

        // Cargar la vista predeterminada (Dashboard)
        loadDashboard();
    }

    /**
     * Método para cargar una vista específica en el centro del BorderPane.
     * @param fxmlPath Ruta del archivo FXML a cargar.
     */
    private void loadInterface(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Configurar el controlador hijo (si es necesario)
            Object controller = loader.getController();
            if (controller instanceof StudentMenuController) {
                ((StudentMenuController) controller).setStudentController(this);
            }

            // Establecer la vista cargada en el centro del BorderPane
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al cargar la interfaz: " + fxmlPath);
        }
    }

    // Métodos para cargar vistas específicas
    public void loadDashboard() {
        loadInterface("/com/acb/ams/Views/Dashboard.fxml");
    }

    public void loadStudentCursos() {
        loadInterface("/com/acb/ams/Views/StudentCursos.fxml");
    }
}
