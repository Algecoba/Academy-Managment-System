package com.acb.ams.Controllers.Professor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.acb.ams.Models.Course;
import com.acb.ams.Models.Model;
import com.acb.ams.Models.Person;

import java.util.Optional;

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
    private ComboBox<Course> cmbCursos;
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
    private ComboBox<Course> cmbCursosAsis;
    @FXML
    private DatePicker FechaAsistencia;
    @FXML
    private Button btnCrearAsistencia;
    @FXML
    private Button btnCancelar;
    @FXML
    private TableView<Person> TablaAsistencia;
    @FXML
    private TableColumn<Person, String> NombreEstudianteAsis;
    @FXML
    private TableColumn<Person, Double> PorcentajeAsistencia;
    @FXML
    private TableColumn<Person, Double> PorcentajeInasistencia;
    @FXML
    private TableColumn<Person, String> Asistencia;
    @FXML
    private Button btnGuardarAsistencia;

    // Criterios
    @FXML
    private AnchorPane ProfessorCriterios;
    @FXML
    private ComboBox<Course> cmbCursoCriterio;
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
    private TableView<String> Criterios;
    @FXML
    private TableColumn<String, String> colNombreCriterio;
    @FXML
    private TableColumn<String, Double> colPesoCriterio;
    @FXML
    private TableColumn<String, String> colDescripCriterio;

    // Dashboard
    @FXML
    private AnchorPane ProffesorDashboard;
    @FXML
    public Label cantAsignaturaslbl;
    @FXML
    public Label cantCursoslbl;

    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private ObservableList<Person> estudiantes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Cargar comboBox de cursos
        courses = Model.getInstance().getDataBase().loadCoursesNames();
        this.cmbCursos.setItems(courses);
        this.cmbCursosAsis.setItems(courses);
        this.cmbCursoCriterio.setItems(courses);

        // Cargar tabla de calificaciones
        this.estudiantes = Model.getInstance().getDataBase().dataForTableActividades();
        this.TablaCalificaciones.setItems(estudiantes);

        // Configurar las columnas de la tabla
        NombreEstudiante.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPerNombres()));
        NotaDefinitiva.setCellValueFactory(
                cellData -> new SimpleDoubleProperty(cellData.getValue().getQualification()).asObject());
        Actividadi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivitie()));

        // Configuración de eventos de los botones
        btnLogout.setOnAction(event -> logOut(event));
        btnCalificaciones.setOnAction(event -> showActividades(event));
        btnAsistencias.setOnAction(event -> showAsistencias(event));
        btnCriterios.setOnAction(event -> showCriterios(event));
    }

    // Cambiar la vista a Dashboard
    @FXML
    private void showDashboard(ActionEvent event) {
        ProffesorDashboard.setVisible(true);
        ProfessorActividades.setVisible(false);
        ProfessorAsistencias.setVisible(false);
        ProfessorCriterios.setVisible(false);
    }

    // Cambiar la vista a Actividades
    @FXML
    private void showActividades(ActionEvent event) {
        ProfessorActividades.setVisible(true);
        ProfessorAsistencias.setVisible(false);
        ProfessorCriterios.setVisible(false);
        ProffesorDashboard.setVisible(false);
        // Obtener el nombre del profesor
        NombreProfesor.setText("Profesor Juan");
    }

    // Cambiar la vista a Asistencias
    @FXML
    private void showAsistencias(ActionEvent event) {
        ProfessorAsistencias.setVisible(true);
        ProfessorActividades.setVisible(false);
        ProfessorCriterios.setVisible(false);
        ProffesorDashboard.setVisible(false);
    }

    // Cambiar la vista a Criterios
    @FXML
    private void showCriterios(ActionEvent event) {
        ProfessorCriterios.setVisible(true);
        ProfessorAsistencias.setVisible(false);
        ProfessorActividades.setVisible(false);
        ProffesorDashboard.setVisible(false);
    }

    // LogOut de la aplicación
    @FXML
    private void logOut(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmar");
        alert.setHeaderText("¿Seguro que desea cerrar sesión?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Model.getInstance().getViewFactory().showLoginWindow();
        }
    }

    // Métodos para manejar eventos específicos de actividades, asistencias y
    // criterios
    @FXML
    private void crearActividad(ActionEvent event) {
        String nombreActividad = NombreActividad.getText();
        String criterio = (String) CriterioActividad.getValue();
        // Implementar lógica de creación de actividad
    }

    @FXML
    private void actualizarActividad(ActionEvent event) {
        String nombreActividad = NombreActividad.getText();
        String criterio = (String) CriterioActividad.getValue();
        // Implementar lógica de actualización de actividad
    }

    @FXML
    private void limpiarFormulario(ActionEvent event) {
        NombreActividad.clear();
        CriterioActividad.getSelectionModel().clearSelection();
        FechaActividad.setValue(null);
    }

    @FXML
    private void guardarNotas(ActionEvent event) {
        // Implementar lógica para guardar las notas en la base de datos
    }

    @FXML
    private void crearCriterio(ActionEvent event) {
        String nombreCriterio = NombreCriterio.getText();
        String pesoCriterio = PesoCriterio.getText();
        String descripcionCriterio = DescripccionCriterio.getText();
        // Implementar lógica de creación del criterio
    }

    @FXML
    private void actualizarCriterio(ActionEvent event) {
        // Implementar lógica para actualizar el criterio
    }

    @FXML
    private void eliminarCriterio(ActionEvent event) {
        // Implementar lógica para eliminar el criterio
    }
}
