package com.acb.ams.Controllers.Admin;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class AdminGestionCursosController {
    //Creación de un pensum
    public TextField NombrePensumTxt;
    public TextField anioTxt;
    public TextField cantAsignaturaTxt;
    //CRUD Pensum
    public Button btnCrearPensum;
    public Button btnEliminarPensum;
    public Button btnActualizarPensum;

    //Creacion de Curso
    public TextField gradoCursoTxt;
    public ComboBox pensuComboBoxTOCurso;
    //CRUD Curso
    public Button btnCrearCurso;
    public Button btnEliminarCurso;
    public Button btnActualizarCurso;

    //Creación de asignatura
    public TextField NombreAsignaturaTxt;
    public TextField NumeroCreditosTxt;
    public ComboBox pensumComboBox;
    //CRUD Asignaturas
    public Button btnCrearAsignatura;
    public Button btnEliminarAsignatura;
    public Button btnActualizarAsignatura;

    //Tabla de visualización de los pensums, cursos y asignaturas
    public TableColumn colNombrePensum;
    public TableColumn colAnioPensum;
    public TableColumn colCantAsigPensum;
    public TableColumn colGradoCurso;
    public TableColumn colNombreAsignatura;
    public TableColumn colCreditosAsignatura;


}
