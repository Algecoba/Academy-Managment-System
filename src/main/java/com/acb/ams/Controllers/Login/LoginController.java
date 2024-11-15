package com.acb.ams.Controllers.Login;

import com.acb.ams.Data.DatabaseConnector;
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
        accountSelector_cmb.getItems().addAll("Estudiante", "Profesor", "Coordinador");
        error_label.setVisible(false);

        // Configura la acción del botón de inicio de sesión
        login_btn.setOnAction(
                e -> onLogin(username_field.getText(), password_field.getText(), accountSelector_cmb.getValue()));
    }

    private void onLogin(String username, String password, String accountType) {
        try {
            // Validación de campos vacíos
            if (username.isEmpty() || password.isEmpty() || accountType == null) {
                error_label.setVisible(true);
                error_label.setText("Por favor, completa todos los campos.");
                return;
            }

            // Verificar usuario en la base de datos
            boolean isValidUser = DatabaseConnector.verifyUser(username, password, accountType);

            if (isValidUser) {
                openWindow(accountType); // Abrir ventana correspondiente
            } else {
                error_label.setVisible(true);
                error_label.setText("Credenciales incorrectas o rol no permitido.");
            }
        } catch (Exception e) {
            error_label.setVisible(true);
            error_label.setText("Ocurrió un error en el inicio de sesión. Verifica la consola.");
            e.printStackTrace();
        }
    }



    public void openWindow(String accountType) {
        Stage stage = (Stage) username_field.getScene().getWindow();
        switch (accountType) {
            case "Estudiante":
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showStudentWindow(stage);
                break;
            case "Profesor":
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showProfessorWindow(stage);
                break;
            case "Coordinador":
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showAdminWindow(stage);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Tipo de cuenta no válido");
                alert.setContentText("El tipo de cuenta proporcionado no es reconocido. Por favor, intente de nuevo.");
                alert.showAndWait();
                break;
        }
    }
}
