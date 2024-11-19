package com.acb.ams.Controllers.Student;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class StudentCursosController {
    //Elige la asignatura, y el historial que quiere ver- Aparece el nombre del docente
    public ComboBox asignaturaComboBox;
    public Label NombreProfesorTxt;
    public ComboBox tipoComboBox;

    //Historial de notas del estudiante en la asignatura elegida
    public AnchorPane notasStPane;
    public TableView TableNotas;
    public TableColumn colNombre;
    public TableColumn colDefinitiva;
    public TableColumn colActividadi;

    //Historial de asistenicas del estudiante en la asignatura elegida
    public AnchorPane asistenciasStPane;
    public TableView TableAsistencias;
    public TableColumn colNombreAsis;
    public TableColumn colPorcentInsasistencias;
    public TableColumn colPorcentAsistencias;
}
