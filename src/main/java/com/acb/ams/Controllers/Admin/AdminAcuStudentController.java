package com.acb.ams.Controllers.Admin;

import javafx.scene.control.*;

public class AdminAcuStudentController {
    //Campo de texto que debe funcionar como barra de busqueda en el table view
    public TextField Idtxt;

    //Campos que aparecen automaticamente cuando se clickean en la tabla
    public Label NombreAlumnolbl;
    public Label ApellidoAlumnolbl;
    public Label CursoAlumnolbl;

    //Lista de personas where tipo="Acudiente"
    public ComboBox Acudiente1comboBox;
    public ComboBox Acudiente2comboBox;

    //Boton para actualizar-asignar los acudientes de los estudiantes y el de limpiar
    public Button btnActualizar;
    public Button btnLimpiar;
    
    //Tabla de visualización de estudiantes con sus acudientes
    //Select personas where tipo="Estudiante"
    public TableColumn colid;
    public TableColumn colNombres;
    public TableColumn colApellidos;
    public TableColumn colCurso;
    public TableColumn colAcudiente1;
    public TableColumn colAcudiente2;


    //
}
