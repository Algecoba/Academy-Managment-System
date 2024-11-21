package com.acb.ams.Controllers.Admin;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXML;

import java.net.URL;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class AdminUsersController implements Initializable {

    public AnchorPane mainPane;
    // Datos generales de todas las personas
    @FXML
    public ComboBox<String> estadoComboBox;
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

    // Botones CRUD y barra (R)ead
    @FXML
    public Button btnEliminar;
    @FXML
    public Button btnActualizar;
    @FXML
    public Button btnLimpiar;
    @FXML
    private Button btnGuardar;
    public TextField txtBuscar;

    // Sección de Contacto
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
     * Sección de matricula del estudiante
     * EstudianteCurso la fecha de matricula.
     */
    @FXML
    private DatePicker fechaMatriculaPicker;
    @FXML
    private ComboBox<String> cursoComboBox;

    // Tabla de visualización y busqueda de personas
    public AnchorPane visualizacionPane;
    @FXML
    private TableView TablePerson;
    @FXML
    public TableColumn columIdentificacion;
    @FXML
    public TableColumn colTipoId;
    @FXML
    public TableColumn colNombre;
    @FXML
    public TableColumn colApellidos;
    @FXML
    public TableColumn colRol;
    @FXML
    public TableColumn colEstado;
    @FXML
    public TableColumn colDepartamento;
    @FXML
    public TableColumn colCiudad;
    @FXML
    public TableColumn colCelular;
    @FXML
    public TableColumn colDireccion;
    @FXML
    public TableColumn colCorreo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Añadir opciones al ComboBox
        tipoUsuarioComboBox.getItems().addAll("Estudiante", "Profesor", "Administrador");
        tipoIdTxt.getItems().addAll("T.I.", "C.C.", "R.C.", "T.EX.");
        estadoComboBox.getItems().addAll("Activo", "Inactivo");

        //tipoUsuarioComboBox.valueProperty().addListener((observable, oldValue, newValue) -> updateUserFields(newValue));
        // Configurar el botón de guardar
        btnGuardar.setOnAction(event -> btnGuardarAction());
    }

    private void btnGuardarAction() {
        captPerson();
    }
    

    private void captPerson(){
        try {
            // Validar los campos obligatorios
            String tipoUsuario = (String) tipoUsuarioComboBox.getValue();
            String identificacion = identificacionTxt.getText().trim();
            String tipoId = (String) tipoIdTxt.getValue();
            String nombres = nombresTxt.getText().trim();
            String apellidos = apellidosTxt.getText().trim();
            String estado = (String) estadoComboBox.getValue();
    
            if (tipoUsuario == null || identificacion.isEmpty() || tipoId == null || nombres.isEmpty() || apellidos.isEmpty() || estado == null) {
                showAlert("Error", "Por favor, complete todos los campos obligatorios.", Alert.AlertType.ERROR);
                return;
            }
    
            // Validar longitud de los campos
            if (identificacion.length() > 10) {
                showAlert("Error", "La identificación no puede tener más de 10 caracteres.", Alert.AlertType.ERROR);
                return;
            }
    
            if (nombres.length() > 255 || apellidos.length() > 255) {
                showAlert("Error", "Los nombres y apellidos no pueden exceder 255 caracteres.", Alert.AlertType.ERROR);
                return;
            }
    
            // Validar formato de identificación (sólo números)
            if (!identificacion.matches("\\d+")) {
                showAlert("Error", "La identificación debe contener solo números.", Alert.AlertType.ERROR);
                return;
            }
    
            // Llamar al método que realiza la inserción en la base de datos
            Model.getInstance().getDataBase().insertPerson(tipoUsuario, identificacion, nombres, apellidos, estado);
    
            // Notificar al usuario
            showAlert("Éxito", "Los datos se han guardado correctamente.", Alert.AlertType.INFORMATION);
    
        } catch (Exception e) {
            showAlert("Error", "Ocurrió un error al guardar los datos: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
