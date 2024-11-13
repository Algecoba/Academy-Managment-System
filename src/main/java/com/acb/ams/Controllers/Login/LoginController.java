package com.acb.ams.Controllers.Login;

import com.acb.ams.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField username_field; 

    @FXML
    private PasswordField password_field;

    @FXML
    private ChoiceBox<String> accountSelector_cmb;

    @FXML
    private Button login_btn;

    @FXML
    private Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Añade opciones al ChoiceBox para seleccionar tipo de cuenta
        accountSelector_cmb.getItems().addAll("Estudiante", "Profesor", "Administrador");

        // Configura la acción del botón de inicio de sesión
        login_btn.setOnAction(e -> onLogin());
    }

    private void onLogin() {
        // Aquí podrías agregar la lógica de autenticación
        String username = username_field.getText();
        String password = password_field.getText();
        String accountType = accountSelector_cmb.getValue();

        // Validación de ejemplo: verifica si el usuario y contraseña no están vacíos
        if (username.isEmpty() || password.isEmpty() || accountType == null) {
            error_label.setText("Por favor, completa todos los campos.");
            return;
        }

        // Si pasa la validación, cierra la ventana actual y abre la ventana del usuario
        Stage stage = (Stage) username_field.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showStudentWindow();
    }
}
