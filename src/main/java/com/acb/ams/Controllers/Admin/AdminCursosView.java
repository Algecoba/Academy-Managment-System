package com.acb.ams.Controllers.Admin;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AdminCursosView {
    //Información básica del curso
    public ComboBox cursoComboBox;
    public ComboBox asignaturaComboBox;
    public Label NombreProfesorTxt;
    public Label cantEstudiantesTxt; //COUNT estudiantes IN curso
    //Este combo es el que decide que vamos a mostrar, si es la asistencia o las notas.
    public ComboBox tipoComboBox;

    //Tabla de visualización de notas
    public AnchorPane notasCursosPane;
    public TableView TableNotas;
    public TableColumn colEstudiantes;
    public TableColumn colDefinitiva;
    public TableColumn colActividadi;

    //Tabla de visualización de asistencias
    public AnchorPane asistenciasCursosPane;
    public TableView TableAsistencias;
    public TableColumn colEstudiantesAsis;
    public TableColumn colPorcentInsasistencias;
    public TableColumn colPorcentAsistencias;

    

}
