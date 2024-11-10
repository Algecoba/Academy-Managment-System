package com.acb.ams.Controllers.Student;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    public Button st_DashboardBtn;
    public Button st_CursesBtn;
    public Button st_ProfileBtn;
    public Button st_LogOutBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        st_DashboardBtn.setOnAction(event -> {});
    }
}
