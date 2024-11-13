package com.acb.ams.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;


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
        ad_LogOutBtn.setOnAction(event -> handleLogOutBtn());
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
    private void handleLogOutBtn() {
        System.out.println("Botón Log out presionado");
        // Lógica para cerrar sesión
    }
}
