package com.acb.ams.Controllers.Admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class AdminMenuController implements Initializable {

    @FXML
    public Button ad_UsersBtn;
    @FXML
    public Button ad_AsiganutrasBtn;
    @FXML
    public Button ad_CursosBtn;
    @FXML
    public Button ad_ReportesBtn;
    @FXML
    public Button ad_ProfileBtn;
    @FXML
    public Button ad_LogOutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Asignar manejadores de eventos a cada botón
        ad_UsersBtn.setOnAction(event -> handleUsersBtn());
        ad_AsiganutrasBtn.setOnAction(event -> handleAsignaturasBtn());
        ad_CursosBtn.setOnAction(event -> handleCursosBtn());
        ad_ReportesBtn.setOnAction(event -> handleReportesBtn());
        ad_ProfileBtn.setOnAction(event -> handleProfileBtn());
        ad_LogOutBtn.setOnAction(event -> handleLogOutBtn(event));
    }

    // Método para manejar el evento del botón Usuarios
    private void handleUsersBtn() {
        System.out.println("Botón Usuarios presionado");
        // Lógica para gestionar usuarios
    }

    // Método para manejar el evento del botón Asignaturas
    private void handleAsignaturasBtn() {
        System.out.println("Botón Asignaturas presionado");
        // Lógica para gestionar asignaturas
    }

    // Método para manejar el evento del botón Cursos
    private void handleCursosBtn() {
        System.out.println("Botón Cursos presionado");
        // Lógica para gestionar cursos
    }

    // Método para manejar el evento del botón Reportes
    private void handleReportesBtn() {
        System.out.println("Botón Reportes presionado");
        // Lógica para generar reportes
    }

    // Método para manejar el evento del botón Perfil
    private void handleProfileBtn() {
        System.out.println("Botón Perfil presionado");
        // Lógica para ver el perfil
    }

    // Método para manejar el evento del botón Log Out
    @FXML
    private void handleLogOutBtn(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lógica para cerrar la sesión y mostrar la ventana de login
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Model.getInstance().getViewFactory().showLoginWindow(null);
        } else {
            System.out.println("Cancelando cierre de sesion.");
        }

    }

}
