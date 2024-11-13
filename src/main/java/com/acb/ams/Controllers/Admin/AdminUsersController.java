package com.acb.ams.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {

    @FXML
    private VBox mainContainer;

    @FXML
    private ComboBox<String> tipoUsuarioComboBox;

    @FXML
    private TextField identificacionTxt;

    @FXML
    private ComboBox<String> tipoIdTxt;

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
        // Configurar el evento de cambio de selección
        tipoUsuarioComboBox.setOnAction(event -> handleUserTypeSelection());
        // Ocultar secciones específicas al iniciar
        studentBox.setVisible(false);
        professorBox.setVisible(false);
    }

    private void handleUserTypeSelection() {
        String selectedType = tipoUsuarioComboBox.getValue();
        // Ocultar todas las secciones por defecto
        studentBox.setVisible(false);
        professorBox.setVisible(false);
        acudienteBox.setVisible(false); // Agrega esta línea si deseas ocultar `acudienteBox` también.
        
        // Mostrar la sección correspondiente al tipo de usuario seleccionado
        switch (selectedType) {
            case "Estudiante":
                studentBox.setVisible(true);
                break;
            case "Profesor":
                professorBox.setVisible(true);
                break;
            case "Administrador":
                // Administrador no necesita ninguna sección extra
                break;
            default:
                break;
        }
    }
}
