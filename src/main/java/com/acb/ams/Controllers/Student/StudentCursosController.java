package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import com.acb.ams.Models.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentCursosController {

    @FXML
    private ComboBox<Subject> asignaturaComboBox;

    @FXML
    private Label NombreProfesorTxt;

    @FXML
    private ComboBox<String> tipoComboBox;

    @FXML
    private AnchorPane notasStPane;

    @FXML
    private TableView<Object> TableNotas;

    @FXML
    private TableColumn<Object, String> colNombre;

    @FXML
    private TableColumn<Object, String> colDefinitiva;

    @FXML
    private TableColumn<Object, String> colActividadi;

    @FXML
    private AnchorPane asistenciasStPane;

    @FXML
    private TableView<Attendance> TableAsistencias;

    @FXML
    private TableColumn<Attendance, String> colNombreAsis;

    @FXML
    private TableColumn<Attendance, String> colPorcentInsasistencias;

    @FXML
    private TableColumn<Attendance, String> colPorcentAsistencias;

    ObservableList<Attendance> attendances = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Inicializa el comboBox con opciones.
        tipoComboBox.getItems().addAll("Asistencia", "Notas");

        // Añade un listener para manejar la lógica al cambiar entre "Asistencia" y "Notas".
        tipoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Asistencia".equals(newValue)) {
                asistenciasStPane.setVisible(true);
                notasStPane.setVisible(false);
            } else if ("Notas".equals(newValue)) {
                asistenciasStPane.setVisible(false);
                notasStPane.setVisible(true);
            }
        });

        // Coloca las asignaturas del estudiante en el combobox
        var modeloBase = Model.getInstance().getDataBase();
        String nombreUsuario = modeloBase.getName();
        ObservableList<Subject> asigns = modeloBase.getSubjectStudent(nombreUsuario);
        this.asignaturaComboBox.setItems(asigns);

        // Agregar lógica adicional si es necesario.
        // Colocar nombre del profesor
        this.asignaturaComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) { // Verifica que se haya seleccionado una asignatura
                String asignaturaSeleccionada = newValue.toString();
                this.NombreProfesorTxt
                        .setText(capitalize(modeloBase.getTeacherName(nombreUsuario, asignaturaSeleccionada)));
                configureAsistenciasTable(nombreUsuario, asignaturaSeleccionada);
            }
        });
    }

    private void configureAsistenciasTable(String nombre, String asignatura) {
        // Cargar los datos de asistencias para la tabla
        attendances = Model.getInstance().getDataBase().getAttendancesForTableCourses(nombre, asignatura);
        TableAsistencias.setItems(attendances);

        // Configurar las columnas de la tabla
        this.colNombreAsis.setCellValueFactory(new PropertyValueFactory<>("asignatura"));
        this.colPorcentAsistencias.setCellValueFactory(new PropertyValueFactory<>("PorcentAsistencias"));
        this.colPorcentInsasistencias.setCellValueFactory(new PropertyValueFactory<>("PorcentInsasistencias"));
    }

    public String capitalize(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre; // Devuelve el mismo valor si es null o vacío
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }
}
