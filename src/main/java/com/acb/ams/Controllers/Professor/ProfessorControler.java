package com.acb.ams.Controllers.Professor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ProfessorControler {

    // Columna para mostrar actividades específicas dentro de la tabla de calificaciones
    @FXML
    private TableColumn<?, ?> Actividadi;

    // Columna en la tabla de asistencias para marcar la asistencia en fechas específicas
    @FXML
    private TableColumn<?, ?> Asistencia;

    // ComboBox para seleccionar el criterio de calificación de una actividad (ej. Examen, Tarea)
    @FXML
    private ComboBox<?> CriterioActividad;

    // Campo de texto para ingresar la descripción de un criterio de calificación
    @FXML
    private TextField DescripccionCriterio;

    // Selector de fecha para establecer la fecha de una actividad (ej. fecha del examen)
    @FXML
    private DatePicker FechaActividad;

    // Selector de fecha para establecer la fecha al tomar asistencia
    @FXML
    private DatePicker FechaAsistencia;

    // Contenedor principal para la parte central de la interfaz del profesor
    @FXML
    private AnchorPane Main;

    // Campo de texto para ingresar el nombre de una nueva actividad
    @FXML
    private TextField NombreActividad;

    // Campo de texto para ingresar el nombre de un criterio de calificación
    @FXML
    private TextField NombreCriterio;

    // Columna en la tabla de calificaciones para mostrar los nombres de los estudiantes
    @FXML
    private TableColumn<?, ?> NombreEstudiante;

    // Columna en la tabla de asistencias para mostrar los nombres de los estudiantes
    @FXML
    private TableColumn<?, ?> NombreEstudianteAsis;

    // Etiqueta para mostrar el nombre del profesor actual en la interfaz
    @FXML
    private Label NombreProfesor;

    // Columna en la tabla de calificaciones para mostrar la nota definitiva del estudiante
    @FXML
    private TableColumn<?, ?> NotaDefinitiva;

    // Campo de texto para ingresar el peso porcentual de un criterio de calificación
    @FXML
    private TextField PesoCriterio;

    // Columna en la tabla de asistencias para mostrar el porcentaje de asistencia del estudiante
    @FXML
    private TableColumn<?, ?> PorcentajeAsistencia;

    // Columna en la tabla de asistencias para mostrar el porcentaje de inasistencias del estudiante
    @FXML
    private TableColumn<?, ?> PorcentajeInasistencia;

    // Panel que contiene la vista de creación y edición de actividades
    @FXML
    private AnchorPane ProfessorActividades;

    // Panel que contiene la vista de control de asistencias
    @FXML
    private AnchorPane ProfessorAsistencias;

    // Panel que contiene la vista para definir y gestionar los criterios de calificación
    @FXML
    private AnchorPane ProfessorCriterios;

    // Panel del dashboard principal del profesor
    @FXML
    private AnchorPane ProffesorDashboard;

    // Menú lateral con las opciones de navegación para el profesor
    @FXML
    private VBox ProffesorMenu;

    // Tabla para mostrar la lista de estudiantes y su estado de asistencia
    @FXML
    private TableView<?> TablaAsistencia;

    // Tabla para mostrar la lista de estudiantes y sus calificaciones por actividad
    @FXML
    private TableView<?> TablaCalificaciones;

    // Botón para actualizar un criterio de calificación existente
    @FXML
    private Button btnActualizar;

    // Botón para abrir la vista de control de asistencias
    @FXML
    private Button btnAsistencias;

    // Botón para abrir la vista de calificaciones y actividades
    @FXML
    private Button btnCalificaciones;

    @FXML
    private Button btnCriterios;

    @FXML
    private Button btnLogout;

    // Botón para cancelar una acción, como crear o actualizar asistencia
    @FXML
    private Button btnCancelar;

    // Botón para crear un nuevo registro de asistencia para una fecha específica
    @FXML
    private Button btnCrearAsistencia;

    // Botón para crear un nuevo criterio de calificación
    @FXML
    private Button btnCrearCriterio;

    // Botón para regresar al dashboard principal del profesor
    @FXML
    private Button btnDashboard;

    // Botón para eliminar un criterio de calificación existente
    @FXML
    private Button btnEliminarCriterio;

    // Botón para guardar las calificaciones ingresadas en la tabla de calificaciones
    @FXML
    private Button btnGuardarNotas;

    // Botón para guardar los registros de asistencia en la tabla de asistencias
    @FXML
    private Button btnGuardarAsistencia;

    // Botón para limpiar el formulario de creación/edición de criterios
    @FXML
    private Button btnLimpiarForm;

    // ComboBox para seleccionar el curso al que se aplican los criterios de calificación
    @FXML
    private ComboBox<?> cmbCursoCriterio;

    // ComboBox para seleccionar el curso actual para registrar o consultar actividades
    @FXML
    private ComboBox<?> cmbCursos;

    // ComboBox para seleccionar el curso actual en el módulo de asistencias
    @FXML
    private ComboBox<?> cmbCursosAsis;
    //Tabla para mostrar los criterios de evaluación
    @FXML
    private TableView<?> Criterios;

    @FXML
    private TableColumn<?, ?> colDescripCriterio;

    @FXML
    private TableColumn<?, ?> colNombreCriterio;

    @FXML
    private TableColumn<?, ?> colPesoCriterio;

}
