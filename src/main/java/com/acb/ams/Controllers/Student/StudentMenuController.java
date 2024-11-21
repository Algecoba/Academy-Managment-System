package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
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

    private StudentController studentController;

    public StudentController studentController() {
        if (studentController == null) {
            Parent root = null;
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Student/Student.fxml"));
                root = loader.load(); // Cargar archivo FXML
                System.out.println("Archivo FXML cargado correctamente.");
                studentController = loader.getController();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar el archivo FXML.");
            }

            if (root == null) {
                System.out.println("Error: Nodo raíz del archivo FXML es null.");
                return null;
            }

        }
        return studentController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignación de acciones a los botones
        addListener();
        
        
        
    }


    private void addListener(){
        st_DashboardBtn.setOnAction(event -> goToDashboard(event));
        st_CursesBtn.setOnAction(event -> goToCourses(event));
        st_ProfileBtn.setOnAction(event -> goToProfile(event));
        st_LogOutBtn.setOnAction(event -> logOut(event));
    }


    public void goToDashboard(javafx.event.ActionEvent event) {
     Model.getInstance().getViewFactory().getStudentSelectedItem().set("Dashboard");
        

    }

    public void goToCourses(javafx.event.ActionEvent event) {
       Model.getInstance().getViewFactory().getStudentSelectedItem().set("Cursos");
    }

    public void goToProfile(javafx.event.ActionEvent event) {
        // loadScene("/path/to/Profile.fxml", event);
        
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
            Model.getInstance().getViewFactory().showLoginWindow();
        } else {
            // Si el usuario cancela, quedarse en el Dashboard
            System.out.println("Cancelando cierre de sesión.");
        }
    }

}
