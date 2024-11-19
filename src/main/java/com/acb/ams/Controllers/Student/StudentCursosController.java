package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StudentCursosController {

    @FXML
    private ComboBox<String> asignaturaComboBox;

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
    private TableView<Object> TableAsistencias;

    @FXML
    private TableColumn<Object, String> colNombreAsis;

    @FXML
    private TableColumn<Object, Double> colPorcentInsasistencias;

    @FXML
    private TableColumn<Object, Double> colPorcentAsistencias;

    @FXML
    public void initialize() {
        // Inicializa el comboBox con opciones.
        tipoComboBox.getItems().addAll("Asistencia", "Notas");

        // Añade un listener para manejar la lógica al cambiar entre "Asistencia" y
        // "Notas".
        tipoComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Asistencia".equals(newValue)) {
                asistenciasStPane.setVisible(true);
                notasStPane.setVisible(false);
            } else if ("Notas".equals(newValue)) {
                asistenciasStPane.setVisible(false);
                notasStPane.setVisible(true);
            }
        });

        // Agregar lógica adicional si es necesario.
    }
}
