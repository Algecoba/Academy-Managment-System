
package com.acb.ams.Controllers.Professor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

import com.acb.ams.Models.Model;

public class ProfessorControler {

    @FXML
    private StackPane rootPane;

    @FXML
    private VBox ProffesorMenu;

    @FXML
    private Label NombreProfesor;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnCalificaciones;

    @FXML
    private Button btnAsistencias;

    @FXML
    private Button btnCriterios;

    @FXML
    private Button btnLogout;

    @FXML
    private AnchorPane Main;

    @FXML
    private AnchorPane ProfessorActividades;

    @FXML
    private ComboBox<String> cmbCursos;

    @FXML
    private TextField NombreActividad;

    @FXML
    private DatePicker FechaActividad;

    @FXML
    private ComboBox<String> CriterioActividad;

    @FXML
    private Button btnCrearActividad;

    @FXML
    private Button btnActualizarActividad;

    @FXML
    private Button btnLimpiar;

    @FXML
    private TableView<?> TablaCalificaciones;

    @FXML
    private Button btnGuardarNotas;

    @FXML
    private AnchorPane ProfessorAsistencias;

    @FXML
    private ComboBox<String> cmbCursosAsis;

    @FXML
    private DatePicker FechaAsistencia;

    @FXML
    private Button btnCrearAsistencia;

    @FXML
    private Button btnCancelar;

    @FXML
    private TableView<?> TablaAsistencia;

    @FXML
    private Button btnGuardarAsistencia;

    @FXML
    private AnchorPane ProfessorCriterios;

    @FXML
    private ComboBox<String> cmbCursoCriterio;

    @FXML
    private TextField NombreCriterio;

    @FXML
    private TextField PesoCriterio;

    @FXML
    private TextField DescripccionCriterio;

    @FXML
    private Button btnCrearCriterio;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnLimpiarForm;

    @FXML
    private Button btnEliminarCriterio;

    @FXML
    private TableView<?> Criterios;

    // Método para inicializar los componentes o establecer la vista inicial
    /**
     * ProfessorActividades.setVisible(true);
     * Main.setVisible(true);
     * ProfessorAsistencias.setVisible(true);
     * ProfessorCriterios.setVisible(true);
     * ProffesorMenu.setVisible(true);
     * 
     */
    public void initialize() {
        NombreProfesor.setText("Profesor Sosa"); // Esto podría ser dinámico, según el login o sesión del profesor.

        // Iniciar vistas según el menú
        // ProfesorDashboardVisible();
        //NOtas Asistencias Criterios
        btnLogout.setOnAction(event -> logOut(event));
        btnCalificaciones.setOnAction(event -> showActividades(event));
        btnAsistencias.setOnAction(event -> showAsistencias(event));
        btnCriterios.setOnAction(event -> showCriterios(event));
        ///btnAsistencias.setOnAction(event -> showAsistencias(event));
    }

    // Cambiar la vista a Dashboard
    @FXML
    private void showDashboard(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Aqui no hay nada");
        //alert.setContentText("El tipo de cuenta proporcionado no es reconocido. Por favor, intente de nuevo.");
        alert.showAndWait();
    }

    // Cambiar la vista a Actividades
    @FXML
    private void showActividades(ActionEvent event) {
        ProfessorActividades.setVisible(true);
        ProfessorAsistencias.setVisible(false);
        ProfessorCriterios.setVisible(false);
    }

    // Cambiar la vista a Asistencias
    @FXML
    private void showAsistencias(ActionEvent event) {
        ProfessorAsistencias.setVisible(true);
        ProfessorActividades.setVisible(false);
        ProfessorCriterios.setVisible(false);
    }

    // Cambiar la vista a Criterios
    @FXML
    private void showCriterios(ActionEvent event) {
        ProfessorCriterios.setVisible(true);
        ProfessorAsistencias.setVisible(false);
        ProfessorActividades.setVisible(false);
    }

    // LogOut de la aplicación
    @FXML
    private void logOut(ActionEvent event) {
        // Mostrar una confirmación de cierre de sesión
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lógica para cerrar la sesión y mostrar la ventana de login
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Model.getInstance().getViewFactory().showLoginWindow(null);
        } else {
            System.out.println("Cancelando cierre de sesion.");
        }

    }

    // Otros métodos para manejar eventos específicos de actividades, asistencias y
    // criterios

    @FXML
    private void crearActividad(ActionEvent event) {
        // Lógica para crear actividad
        String nombreActividad = NombreActividad.getText();
        String criterio = CriterioActividad.getValue();
        // Aquí deberías implementar la lógica de crear la actividad
    }

    @FXML
    private void actualizarActividad(ActionEvent event) {
        // Lógica para actualizar actividad
        String nombreActividad = NombreActividad.getText();
        String criterio = CriterioActividad.getValue();
        // Implementar la lógica para actualizar la actividad
    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {
        // Limpiar campos de actividad
        NombreActividad.clear();
        CriterioActividad.getSelectionModel().clearSelection();
        FechaActividad.setValue(null);
    }

    @FXML
    private void guardarNotas(ActionEvent event) {
        // Lógica para guardar las notas
        // Implementar la lógica de guardar notas en la base de datos
    }

    @FXML
    private void crearCriterio(ActionEvent event) {
        // Lógica para crear un nuevo criterio
        String nombreCriterio = NombreCriterio.getText();
        String pesoCriterio = PesoCriterio.getText();
        String descripcionCriterio = DescripccionCriterio.getText();
        // Implementar la lógica de crear el criterio
    }

    @FXML
    private void actualizarCriterio(ActionEvent event) {
        // Lógica para actualizar criterio
        // Implementar la lógica de actualizar el criterio
    }

    @FXML
    private void eliminarCriterio(ActionEvent event) {
        // Lógica para eliminar criterio
        // Implementar la lógica de eliminar el criterio
    }
}
