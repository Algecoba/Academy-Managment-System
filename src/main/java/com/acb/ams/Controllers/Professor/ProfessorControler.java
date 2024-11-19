
package com.acb.ams.Controllers.Professor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
//import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Optional;

import com.acb.ams.Models.Activities;
import com.acb.ams.Models.Course;
import com.acb.ams.Models.Model;
import com.acb.ams.Models.Person;

//import eu.hansolo.toolbox.observables.ObservableList;

public class ProfessorControler {

    // Menú de navegación del profesor
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

    // Main content area
    @FXML
    private AnchorPane Main;

    // Actividades
    @FXML
    private AnchorPane ProfessorActividades;
    @FXML
    private ComboBox cmbCursos;
    @FXML
    private TextField NombreActividad;
    @FXML
    private DatePicker FechaActividad;
    @FXML
    private ComboBox CriterioActividad;
    @FXML
    private Button btnCrearActividad;
    @FXML
    private Button btnActualizarActividad;
    @FXML
    private Button btnLimpiar;
    @FXML
    private TableView<Person> TablaCalificaciones;
    @FXML
    private TableColumn<Person, String> NombreEstudiante;

    @FXML
    private TableColumn<Person, Double> NotaDefinitiva;
    @FXML
    private TableColumn<Person, String> Actividadi;
    @FXML
    private Button btnGuardarNotas;

    // Asistencias
    @FXML
    private AnchorPane ProfessorAsistencias;
    @FXML
    private ComboBox cmbCursosAsis;
    @FXML
    private DatePicker FechaAsistencia;
    @FXML
    private Button btnCrearAsistencia;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView TablaAsistencia;
    @FXML
    private TableColumn NombreEstudianteAsis;
    @FXML
    private TableColumn PorcentajeAsistencia;
    @FXML
    private TableColumn PorcentajeInasistencia;
    @FXML
    private TableColumn Asistencia;
    @FXML
    private Button btnGuardarAsistencia;

    // Criterios
    @FXML
    private AnchorPane ProfessorCriterios;
    @FXML
    private ComboBox cmbCursoCriterio;
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
    private TableView Criterios;
    @FXML
    private TableColumn colNombreCriterio;
    @FXML
    private TableColumn colPesoCriterio;
    @FXML
    private TableColumn colDescripCriterio;

    // Dashboard
    @FXML
    private AnchorPane ProffesorDashboard;
    @FXML
    public Label cantAsignaturaslbl;
    @FXML
    public Label cantCursoslbl;

    // Método para inicializar los componentes o establecer la vista inicial
    /**
     * ProfessorActividades.setVisible(true);
     * Main.setVisible(true);
     * ProfessorAsistencias.setVisible(true);
     * ProfessorCriterios.setVisible(true);
     * ProffesorMenu.setVisible(true);
     * 
     */

    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private ObservableList<Person> estudiantes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Cargar comboBox
        courses = Model.getInstance().loadCoursesNames();
        this.cmbCursos.setItems(courses);

        // Cargar tabla
        this.estudiantes = Model.getInstance().dataForTableActividades();
        this.TablaCalificaciones.setItems(estudiantes);

        //Configurar Propiedades
        NombreEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerNombres()));
        NotaDefinitiva.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getQualification()).asObject());
        Actividadi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivitie()));

        // Botones
        btnLogout.setOnAction(event -> logOut(event));
        btnCalificaciones.setOnAction(event -> showActividades(event));
        btnAsistencias.setOnAction(event -> showAsistencias(event));
        btnCriterios.setOnAction(event -> showCriterios(event));
        /// btnAsistencias.setOnAction(event -> showAsistencias(event));
    }

    // Cambiar la vista a Dashboard
    @FXML
    private void showDashboard(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Aqui no hay nada");
        // alert.setContentText("El tipo de cuenta proporcionado no es reconocido. Por
        // favor, intente de nuevo.");
        alert.showAndWait();
    }

    // Cambiar la vista a Actividades
    @FXML
    private void showActividades(ActionEvent event) {

        // Cargar Ventana
        ProfessorActividades.setVisible(true);
        ProfessorAsistencias.setVisible(false);
        ProfessorCriterios.setVisible(false);

        // Obtener nombre del profesor:
        // Cargar comboboxes
        NombreProfesor.setText("Profesor Juan");
        // Verificar los datos
        System.out.println("Se insertaron los datos?");

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
        String criterio = (String) CriterioActividad.getValue();
        // Aquí deberías implementar la lógica de crear la actividad
    }

    @FXML
    private void actualizarActividad(ActionEvent event) {
        // Lógica para actualizar actividad
        String nombreActividad = NombreActividad.getText();
        String criterio = (String) CriterioActividad.getValue();
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
