package com.acb.ams.Controllers;

import com.acb.ams.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public TextField username_field;
    public PasswordField password_Field;
    public ChoiceBox accountSelector_cmb;
    public Button login_btn;
    public Label error_label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accountSelector_cmb.getItems().addAll("Estudiante", "Profesor", "Administrador");
        login_btn.setOnAction(e -> Model.getInstance().getViewFactory().showStudentWindow());
    }

    private void onLogin(){
        Stage stage = (Stage) username_field.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showStudentWindow();
    }


}
