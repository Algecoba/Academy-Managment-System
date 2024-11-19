package com.acb.ams.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class AdminUsersController implements Initializable {

    public AnchorPane mainPane;
    //Datos generales de todas las personas
    @FXML
    public ComboBox estadoComboBox;
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

    //Botones CRUD y barra (R)ead
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnLimpiar;
    @FXML
    private Button btnGuardar;
    public TextField txtBuscar;

    //Sección de Contacto
    @FXML
    public ComboBox CiudadesComboBox;
    @FXML
    public ComboBox DepartamentosComboBox;
    @FXML
    private TextField direccionTxt;
    @FXML
    private TextField correoTxt;
    @FXML
    private TextField celularTxt;

    /*
    Sección de matricula del estudiante
    EstudianteCurso la fecha de matricula.
     */
    @FXML
    private DatePicker fechaMatriculaPicker;
    @FXML
    private ComboBox<String> cursoComboBox;

    //Tabla de visualización y busqueda de personas
    public AnchorPane visualizacionPane;
    public TableColumn columIdentificacion;
    public TableColumn colTipoId;
    public TableColumn colNombre;
    public TableColumn colApellidos;
    public TableColumn colRol;
    public TableColumn colEstado;
    public TableColumn colDepartamento;
    public TableColumn colCiudad;
    public TableColumn colCelular;
    public TableColumn colDireccion;
    public TableColumn colCorreo;

    

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Añadir opciones al ComboBox
        tipoUsuarioComboBox.getItems().addAll("Estudiante", "Profesor", "Administrador");
        tipoIdTxt.getItems().addAll("T.I.", "C.C.", "R.C.", "T.EX.");

        // Configurar el botón de guardar
        btnGuardar.setOnAction(event -> btnGuardarAction());
    }


    private void btnGuardarAction() {
        try {
            // Validar los campos necesarios antes de guardar
            if (tipoUsuarioComboBox.getValue() == null || identificacionTxt.getText().isEmpty() || tipoIdTxt.getValue() == null) {
                showAlert("Error", "Por favor, complete todos los campos obligatorios.", Alert.AlertType.ERROR);
                return;
            }
            //Insertar datos en el modelo

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
