package com.acb.ams.Controllers.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.acb.ams.Models.Model;
import com.acb.ams.Models.Qualification;
import com.acb.ams.Models.Subject;
import com.acb.ams.Models.Activities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class DashboardController {

    @FXML
    private Text saludoNombreTxt;

    @FXML
    private ListView<Subject> CoursesListView;

    @FXML
    private Label promedio1lbl;

    @FXML
    private Label promedio2lbl;

    @FXML
    private Label promedio3lbl;

    @FXML
    private Label promedio4lbl;

    @FXML
    private Label loginDate;

    @FXML
    private TableView<Activities> actividadesTable;

    @FXML
    private TableColumn<Activities, String> colAsignatura;

    @FXML
    private TableColumn<Activities, String> colActividad;

    @FXML
    private TableColumn<Activities, String> colFecha;

    @FXML
    private TableColumn<Activities, Double> colNota;

    @FXML
    private TableColumn<Activities, String> colCriterio;

    private ObservableList<Activities> activities;

    @FXML
    public void initialize() {
        String nombre = Model.getInstance().getDataBase().getName();
        loginDate.setText(fechaActual());

        // Configurar saludo inicial
        saludoNombreTxt.setText("Hola, " + capitalize(nombre));

        // Configurar la lista de cursos
        CoursesListView.setItems(Model.getInstance().getDataBase().getSubjectStudent(nombre));

        // Configurar los promedios
        setPromedios(Model.getInstance().getDataBase().getAveranges(nombre));

        // Configurar la tabla de actividades
        configureActivitiesTable(nombre);
    }

    private void setPromedios(Qualification[] averages) {
        if (averages != null && averages.length == 4) {
            promedio1lbl.setText(averages[0].toString());
            promedio2lbl.setText(averages[1].toString());
            promedio3lbl.setText(averages[2].toString());
            promedio4lbl.setText(averages[3].toString());
        } else {
            // Manejo de casos en los que los promedios no estén disponibles
            promedio1lbl.setText("N/A");
            promedio2lbl.setText("N/A");
            promedio3lbl.setText("N/A");
            promedio4lbl.setText("N/A");
        }
    }

    private void configureActivitiesTable(String nombre) {
        activities = Model.getInstance().getDataBase().getActivitiesForTableDashboard(nombre);
        if (activities != null) {
            actividadesTable.setItems(activities);
        }

        // Configurar columnas de la tabla usando lambdas
        colAsignatura.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAsignatura()));
        colActividad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActNombre()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActFecha().toString()));
        colNota.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getActNota()));
        colCriterio.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCriterio()));
    }

    public String capitalize(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return nombre;
        }
        return nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
    }

    public String fechaActual() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
