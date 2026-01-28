package com.acb.ams.Controllers.Login;

//import com.acb.ams.Data.DatabaseConnector;
import com.acb.ams.Models.Model;
//import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LoginController extends AnchorPane implements Initializable {

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

        // PEDIR EL USUARIO PARA PROBAR
        String[] options = { "NINGUNO", "JOSEP", "JORGE", "---" };

        // Mostrar el JOptionPane
        int choice = JOptionPane.showOptionDialog(null,
                "SELECCIONAR UNA OPCION:", "ESCOGER USUARIO RAPIDO",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, options[0]);

        switch (choice) {
            case 1 -> {
                username_field.setText("josep");
                password_field.setText("1234");
                accountSelector_cmb.setValue("Estudiante");
                break;
            }
            case 2 -> {
                username_field.setText("jorge");
                password_field.setText("1234");
                accountSelector_cmb.setValue("Coordinador");
                break;
            }
        }

        // Configura la acción del botón de inicio de sesión
        login_btn.setOnAction(
                e -> onLogin(username_field.getText(), password_field.getText(), accountSelector_cmb.getValue()));

        setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                login_btn.fire();
            }
        });

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
            boolean isValidUser = Model.getInstance().getDataBase().verifyUser(username, password, accountType);
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
                Model.getInstance().getViewFactory().showStudentWindow();
                break;
            case "Profesor":
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showProfessorWindow();
                break;
            case "Coordinador":
                Model.getInstance().getViewFactory().closeStage(stage);
                Model.getInstance().getViewFactory().showAdminWindow();
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
