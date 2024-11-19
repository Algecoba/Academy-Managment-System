package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class StudentMenuController implements Initializable {

    @FXML
    private Button st_DashboardBtn;

    @FXML
    private Button st_CursesBtn;

    @FXML
    private Button st_ProfileBtn;

    @FXML
    private Button st_LogOutBtn;

    @FXML
    private AnchorPane root;

    private StudentController studentController;

    // Método para recibir el controlador principal
    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignación de acciones a los botones

        st_DashboardBtn.setOnAction(event -> goToDashboard(event));
        st_CursesBtn.setOnAction(event -> goToCourses(event));
        st_ProfileBtn.setOnAction(event -> goToProfile(event));
        st_LogOutBtn.setOnAction(event -> logOut(event));
    }

    public void goToDashboard(javafx.event.ActionEvent event) {
        // loadScene("/path/to/Dashboard.fxml", event);
        studentController.loadDashboard();

    }

    public void goToCourses(javafx.event.ActionEvent event) {
        studentController.loadStudentCursos();
    }

    public void goToProfile(javafx.event.ActionEvent event) {
        // loadScene("/path/to/Profile.fxml", event);
        System.out.println("perfil press");
    }

    public void logOut(javafx.event.ActionEvent event) {
        // Crear el cuadro de diálogo de confirmación
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrar Sesion");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");
        alert.setContentText("Hasta la Proxima!");

        // Mostrar el cuadro de diálogo y esperar la respuesta
        Optional<ButtonType> result = alert.showAndWait();

        // Verificar la respuesta del usuario
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Si el usuario acepta, cerrar la ventana actual y mostrar la ventana de login
            System.out.println("Cerrando sesión...");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Model.getInstance().getViewFactory().showLoginWindow(null);
        } else {
            // Si el usuario cancela, quedarse en el Dashboard
            System.out.println("Cancelando cierre de sesión.");
        }
    }

}
