package com.acb.ams;

import com.acb.ams.Models.Model;


import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Model.getInstance().getViewFactory().showLoginWindow();
        
    }

    public static void main(String[] args) {
        // Lanza la aplicación
        launch(args);
    }
}
