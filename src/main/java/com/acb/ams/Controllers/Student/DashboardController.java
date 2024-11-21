package com.acb.ams.Controllers.Student;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.acb.ams.Models.Model;
import com.acb.ams.Models.Qualification;
import com.acb.ams.Models.Subject;
import com.acb.ams.Models.Activities; // Asegúrate de importar Activities

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
    private TableView<Activities> actividadesTable; // Cambiado para trabajar con Activities

    @FXML
    private TableColumn colAsignatura;

    @FXML
    private TableColumn colActividad;

    @FXML
    private TableColumn colFecha;

    @FXML
    private TableColumn colNota;

    @FXML
    private TableColumn colCriterio;


    ObservableList <Activities >activities;


    @FXML
    public void initialize() {
        String nombre = Model.getInstance().getDataBase().getName();
        loginDate.setText(fechaActual());

        // Configurar saludo inicial
        saludoNombreTxt.setText("Hola, " + capitalize(nombre));

        // Configurar la lista de cursos

        CoursesListView.setItems(Model.getInstance().getDataBase().getSubjectStudent(nombre));

        // Configurar los promedios
        Qualification[] averages = Model.getInstance().getDataBase().getAveranges(nombre);
        promedio1lbl.setText("" + averages[0].toString());
        promedio2lbl.setText("" + averages[1].toString());
        promedio3lbl.setText("" + averages[2].toString());
        promedio4lbl.setText("" + averages[3].toString());

        // Configurar columnas de la tabla (asociar las columnas con los datos
        // correctos)
        activities = Model.getInstance().getDataBase().getActivitiesForTableDashboard(nombre);

        actividadesTable.setItems(activities);

        this.colAsignatura.setCellValueFactory(new PropertyValueFactory("asignatura"));
        this.colActividad.setCellValueFactory(new PropertyValueFactory("actNombre"));
        this.colFecha.setCellValueFactory(new PropertyValueFactory("actFecha"));
        this.colNota.setCellValueFactory(new PropertyValueFactory("actNota"));
        this.colCriterio.setCellValueFactory(new PropertyValueFactory("criterio"));
        

    }

    public void cargarDatos(){
        
    }
   
    

    public String capitalize(String nombre) {
        String name = nombre;
        if (name == null || nombre.isEmpty()) {
            return name; // Devuelve el mismo valor si es null o vacío
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    public String fechaActual() {
        // Configurar la fecha y hora actuales
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        return currentDateTime;
    }

    public void dashboardControllerInstancia() {
        // Método vacío, puedes implementarlo si es necesario
    }
}
