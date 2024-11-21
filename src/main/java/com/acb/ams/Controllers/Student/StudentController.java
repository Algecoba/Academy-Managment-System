package com.acb.ams.Controllers.Student;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import com.acb.ams.Models.Model;

public class StudentController implements Initializable {

    // Referencia al AnchorPane en la sección 'center'

    @FXML
    public BorderPane studentParent;

    @FXML
    private AnchorPane leftAnchorPane; // Referencia al AnchorPane en la sección 'left'

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Model.getInstance().getViewFactory().getStudentSelectedItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case "Cursos":
                    studentParent.setCenter(Model.getInstance().getViewFactory().getCourseStudent());

                    break;

                default:
                    studentParent.setCenter(Model.getInstance().getViewFactory().getDashboardStudent());
                    break;
            }
        });
    }

}
