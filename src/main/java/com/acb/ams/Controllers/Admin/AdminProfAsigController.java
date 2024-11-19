package com.acb.ams.Controllers.Admin;

import javafx.scene.control.*;

public class AdminProfAsigController {
    //Datos del profesor
    public Label NombreProfesorlbl;
    public Label ApellidoProfesorlbl;
    public TextField Idtxt;

    //Combo con la lista de asignaturas creadas, botones para asignar y limpiar
    public ComboBox AsignaturaComboBox;
    public Button btnAsignar;
    public Button btnLimpiar;

    //Tabla de visualización
    public TableColumn colid;
    public TableColumn colNombres;
    public TableColumn colApellidos;
    public TableColumn colCurso;
    public TableColumn colAsignaturas;
}
