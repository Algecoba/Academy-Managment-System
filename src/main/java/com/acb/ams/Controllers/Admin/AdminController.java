package com.acb.ams.Controllers.Admin;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class AdminController {
    
    @FXML
    private BorderPane borderPane;  // El BorderPane que contiene los fx:include

    @FXML
    private VBox AdminMenu;  // Para referenciar el StudentMenu (si es necesario)

    @FXML
    private VBox dashboard;  // Para referenciar el Dashboard (si es necesario)

    // Método de inicialización
    @FXML
    public void initialize() {
        // Aquí podrías cargar lógicas de inicialización si es necesario, como manejar eventos
        System.out.println("MainController inicializado correctamente.");
    }

    // Si necesitas manipular los elementos, por ejemplo, cambiar el contenido del 'center'
    public void setDashboardContent(VBox newContent) {
        borderPane.setCenter(newContent); // Cambia el contenido central dinámicamente
    }
}
