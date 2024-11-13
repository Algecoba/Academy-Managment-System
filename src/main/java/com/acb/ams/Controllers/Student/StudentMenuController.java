package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;

import java.net.URL;
import java.util.ResourceBundle;

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
    private AnchorPane root; // Si necesitas manipular el AnchorPane en algún momento

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignación de acciones a los botones
        st_DashboardBtn.setOnAction(event -> goToDashboard(event));
        st_CursesBtn.setOnAction(event -> goToCourses(event));
        st_ProfileBtn.setOnAction(event -> goToProfile(event));
        st_LogOutBtn.setOnAction(event -> logOut(event));
    }

    private void goToDashboard(javafx.event.ActionEvent event) {
        loadScene("/path/to/Dashboard.fxml", event);
    }

    private void goToCourses(javafx.event.ActionEvent event) {
        loadScene("/path/to/Courses.fxml", event);
    }

    private void goToProfile(javafx.event.ActionEvent event) {
        loadScene("/path/to/Profile.fxml", event);
    }

    private void logOut(javafx.event.ActionEvent event) {
        // Aquí puedes agregar la lógica de cierre de sesión
        System.out.println("Cerrando sesión...");
        loadScene("/path/to/Login.fxml", event);
    }

    private void loadScene(String fxmlFile, javafx.event.ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Cambiar la escena en la ventana actual
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Manejo básico de errores
        }
    }
}
