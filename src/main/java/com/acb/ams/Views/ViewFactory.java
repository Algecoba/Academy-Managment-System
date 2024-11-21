package com.acb.ams.Views;


import com.acb.ams.Controllers.Admin.AdminController;
import com.acb.ams.Controllers.Login.LoginController;
import com.acb.ams.Controllers.Professor.ProfessorControler;
import com.acb.ams.Controllers.Student.StudentController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import javax.sound.midi.VoiceStatus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    //Student Views
    private final StringProperty studentSelectedMenuItem;
    private AnchorPane dashboardStudent;
    private AnchorPane courseStudent;


    //Teacher Views


    // Implementación del patrón Singleton para garantizar que solo haya una instancia
    private static ViewFactory instance;


    public AnchorPane getDashboardStudent(){
        if (dashboardStudent == null) {
            try {
                dashboardStudent = new FXMLLoader(getClass().getResource("/Fxml/Student/Dashboard.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dashboardStudent;
    }

    public AnchorPane getCourseStudent(){
        if (courseStudent == null) {
           try {
            courseStudent = new FXMLLoader(getClass().getResource("/Fxml/Student/StudentCursos.fxml")).load();
           } catch (Exception e) {
            e.printStackTrace();
           } 
        }
        return courseStudent;
    }


    public StringProperty getStudentSelectedItem(){
        return studentSelectedMenuItem;
    }
    public ViewFactory(){
        this.studentSelectedMenuItem = new SimpleStringProperty("");
    }

    public ViewFactory getInstance() {
        
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    
    /**
     * Método basico para mostrar las ventanas
     * 
     * @param fxmlPath Ruta al archivo FXML que queremos cargar.
     */
    private void showWindow(FXMLLoader loader) {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("AMS");
        stage.show();
    }
    


    // Método para mostrar la ventana de "Student" 
    public void showStudentWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Student/Student.fxml"));
        StudentController studentController = new StudentController();
        loader.setController(studentController);
        showWindow(loader);
    }

    // Método para mostrar la ventana de "Profesor" 
    public void showProfessorWindow() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Professor/Professor.fxml"));
        ProfessorControler professorControler = new ProfessorControler();
        loader.setController(professorControler);
        showWindow(loader);
    }

    //Metodo para mostrar la ventana de "Login" 
    public void showLoginWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login/Login.fxml"));
        LoginController loginController = new LoginController();
        loader.setController(loginController);
        showWindow(loader);
    }

    //Metodo para mostrar la ventana de "Admin"
    public void showAdminWindow(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Admin/Admin.fxml"));
        AdminController adminController = new AdminController();
        loader.setController(adminController);
        showWindow(loader);
    }

    

    /**
     * Método para cerrar la ventana (Stage)
     * 
     * @param stage La ventana (Stage) que se desea cerrar.
     */
    public void closeStage(Stage stage) {
        if (stage != null) {
            stage.close(); // Cerrar la ventana
        }
    }



}
