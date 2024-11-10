package com.acb.ams.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminUsersController implements Initializable {
    public VBox mainContainer;
    public ComboBox<String> tipoUsuarioComboBox;
    public TextField identificacionTxt;
    public ComboBox<String> tipoIdTxt;
    public TextField nombresTxt;
    public TextField apellidosTxt;
    public TextField direccionTxt;
    public TextField barrioTxt;
    public TextField ciudadTxt;
    public TextField correoTxt;
    public TextField celularTxt;
    public Button btnGuardar;
    public Button btnCancelar;
    public VBox studentBox;
    public DatePicker fechaMatriculaPicker;
    public ComboBox<String> cursoComboBox;
    public VBox professorBox;
    public ListView<String> asignaturasListView;
    public DatePicker fechaContratacionPicker;
    public VBox acudienteBox;

    @FXML
    private Pane estudianteSectionPane;
    @FXML
    private Pane profesorSectionPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void initialize() {
        // Añadir opciones al ComboBox
        tipoUsuarioComboBox.getItems().addAll("Estudiante", "Profesor", "Administrador");
        // Configurar el evento de cambio de selección
        tipoUsuarioComboBox.setOnAction(event -> handleUserTypeSelection());
        // Ocultar secciones específicas al iniciar
        estudianteSectionPane.setVisible(false);
        profesorSectionPane.setVisible(false);
    }

    private void handleUserTypeSelection() {
        String selectedType = tipoUsuarioComboBox.getValue();
        // Ocultar todas las secciones por defecto
        estudianteSectionPane.setVisible(false);
        profesorSectionPane.setVisible(false);
        // Mostrar la sección correspondiente al tipo de usuario seleccionado
        switch (selectedType) {
            case "Estudiante":
                estudianteSectionPane.setVisible(true);
                break;
            case "Profesor":
                profesorSectionPane.setVisible(true);
                break;
            case "Administrador":
                break;
            default:
                break;
        }
    }
}
