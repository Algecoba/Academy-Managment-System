package com.acb.ams.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class AdminUsersController implements Initializable {

    @FXML
    private VBox mainContainer;

    @FXML
    private ComboBox<String> tipoUsuarioComboBox;

    @FXML
    private TextField identificacionTxt;

    @FXML
    private ComboBox<String> tipoIdTxt; // Renombrado para claridad

    @FXML
    private TextField nombresTxt;

    @FXML
    private TextField apellidosTxt;

    @FXML
    private TextField direccionTxt;

    @FXML
    private TextField barrioTxt;

    @FXML
    private TextField ciudadTxt;

    @FXML
    private TextField correoTxt;

    @FXML
    private TextField celularTxt;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private VBox studentBox;

    @FXML
    private DatePicker fechaMatriculaPicker;

    @FXML
    private ComboBox<String> cursoComboBox;

    @FXML
    private VBox professorBox;

    @FXML
    private ListView<String> asignaturasListView;

    @FXML
    private DatePicker fechaContratacionPicker;

    @FXML
    private VBox acudienteBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Añadir opciones al ComboBox
        tipoUsuarioComboBox.getItems().addAll("Estudiante", "Profesor", "Administrador");
        tipoIdTxt.getItems().addAll("Tarjeta de Identidad", "Cédula", "Registro civil", "Tarjeta de extranjería");

        // Configurar el evento de cambio de selección
        tipoUsuarioComboBox.setOnAction(event -> typeUserSelection());

        // Configurar el botón de guardar
        btnGuardar.setOnAction(event -> btnGuardarAction());

        // Ocultar secciones específicas al iniciar
        studentBox.setVisible(false);
        professorBox.setVisible(false);
        acudienteBox.setVisible(false);
    }

    private void typeUserSelection() {
        // Obtener el tipo de usuario seleccionado
        String typeUser = tipoUsuarioComboBox.getValue();

        // Ocultar todas las secciones por defecto
        studentBox.setVisible(false);
        professorBox.setVisible(false);
        acudienteBox.setVisible(false);

        // Mostrar la sección correspondiente al tipo de usuario seleccionado
        if ("Estudiante".equals(typeUser)) {
            studentBox.setVisible(true);
        } else if ("Profesor".equals(typeUser)) {
            professorBox.setVisible(true);
        }
    }

    private void btnGuardarAction() {
        try {
            // Validar los campos necesarios antes de guardar
            if (tipoUsuarioComboBox.getValue() == null || identificacionTxt.getText().isEmpty() || tipoIdTxt.getValue() == null) {
                showAlert("Error", "Por favor, complete todos los campos obligatorios.", Alert.AlertType.ERROR);
                return;
            }

            // Insertar datos en el modelo
            Model.getInstance().insertPerson(
                tipoUsuarioComboBox.getValue(),
                identificacionTxt.getText(),
                tipoIdTxt.getValue(),
                nombresTxt.getText(),
                apellidosTxt.getText(),
                direccionTxt.getText(),
                barrioTxt.getText(),
                ciudadTxt.getText(),
                correoTxt.getText(),
                celularTxt.getText(),
                fechaMatriculaPicker.getValue(),
                cursoComboBox.getValue(),
                fechaContratacionPicker.getValue()
            );

            showAlert("Éxito", "Los datos se han guardado correctamente.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            showAlert("Error", "Ocurrió un error al guardar los datos: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
