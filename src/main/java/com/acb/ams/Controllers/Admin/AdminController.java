package com.acb.ams.Controllers.Admin;

import com.acb.ams.Models.Model;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class AdminController {

    @FXML
    private BorderPane adminParent; // El BorderPane que contiene los fx:include

    // Para referenciar el Dashboard (si es necesario)

    // Método de inicialización
    @FXML
    public void initialize() {
        Model.getInstance().getViewFactory().getAdminSelectMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "UserView":
                    adminParent.setCenter(Model.getInstance().getViewFactory().getAdminUserView());

                    break;

                case "CursosView":
                    adminParent.setCenter(Model.getInstance().getViewFactory().getAdminGestionCursos());
                    break;
                default:
                    
                    break;
            }
        });
    }

}
