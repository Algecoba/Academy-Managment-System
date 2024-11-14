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

        error_label.setVisible(false);
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
            error_label.setVisible(true);
            error_label.setText("Por favor, completa todos los campos.");
            return;
        }else{
            error_label.setVisible(false);
        }


        // Si pasa la validación, cierra la ventana actual y abre la ventana del usuario
        //Aqui debo colocar un swicth o un if para gestionar que ventana se abre
        Stage stage = (Stage) username_field.getScene().getWindow();
        switch (accountType) {
            case "Estudiante":
                Model.getInstance().getViewFactory().closeStage(stage);  // Cerrar ventana actual
                Model.getInstance().getViewFactory().showStudentWindow(stage);  // Mostrar ventana estudiante
                break;
        
            case "Profesor":
                Model.getInstance().getViewFactory().closeStage(stage);  // Cerrar ventana actual
                Model.getInstance().getViewFactory().showProfessorWindow(stage);  // Mostrar ventana profesor
                break;
        
            case "Administrador":
                Model.getInstance().getViewFactory().closeStage(stage);  // Cerrar ventana actual
                Model.getInstance().getViewFactory().showAdminWindow(stage);  // Mostrar ventana administrador
                break;
        
            default:
                // Mostrar un mensaje de alerta si el tipo de cuenta no es válido
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tipo de cuenta no válido");
                alert.setContentText("El tipo de cuenta proporcionado no es reconocido. Por favor, intente de nuevo.");
                alert.showAndWait();
                break;
        }
        

    }
}
