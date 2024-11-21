package com.acb.ams.Views;


import com.acb.ams.Controllers.Admin.AdminController;
import com.acb.ams.Controllers.Login.LoginController;
import com.acb.ams.Controllers.Professor.ProfessorControler;
import com.acb.ams.Controllers.Student.StudentController;
import com.almasb.fxgl.achievement.Achievement;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

//import javax.sound.midi.VoiceStatus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.PopupWindow.AnchorLocation;

public class ViewFactory {

    //Student Views
    private final StringProperty studentSelectedMenuItem;
    private AnchorPane dashboardStudent;
    private AnchorPane courseStudent;


    //Admin Views
    private final StringProperty adminSelectedMenuItem;
    private AnchorPane  adminUserView;
    private AnchorPane adminCursosView;
    private AnchorPane adminGestionCursos;
    private AnchorPane AdminProfAsig;

    // Implementación del patrón Singleton para garantizar que solo haya una instancia
    private static ViewFactory instance;



    //Interfaces Student
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
    
    //Interfaces Admin
    public AnchorPane getAdminUserView(){
        if (adminUserView == null) {
            try {
                adminUserView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminUserView.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return adminUserView;
    }
    
    public StringProperty getAdminSelectMenuItem(){
        return adminSelectedMenuItem;
    }
    
    public AnchorPane getAdminGestionCursos(){
        if (adminCursosView == null) {
            try {
                adminCursosView = new FXMLLoader(getClass().getResource("/Fxml/Admin/AdminGestionCursos.fxml")).load();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return adminCursosView;
    }
    
    
    
    public ViewFactory(){
        this.studentSelectedMenuItem = new SimpleStringProperty("");
        this.adminSelectedMenuItem = new SimpleStringProperty("");
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
