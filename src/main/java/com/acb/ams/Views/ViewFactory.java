package com.acb.ams.Views;
import com.acb.ams.Controllers.Login.LoginController;
import com.acb.ams.Controllers.Professor.ProfessorController;
import com.acb.ams.Controllers.Student.StudentController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    // Implementación del patrón Singleton para garantizar que solo haya una instancia
    private static ViewFactory instance;

    public ViewFactory() {
        // Constructor vacio
    }

    

    public static ViewFactory getInstance() {
        if (instance == null) {
            instance = new ViewFactory();
        }
        return instance;
    }

    /**
     * Método basico para mostrar las ventanas
     * 
     * @param fxmlPath Ruta al archivo FXML que queremos cargar.
     * @param controller El controlador que queremos inyectar en el FXML.
     */
    private void showWindow(String fxmlPath, Object controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            if (controller != null) {
                loader.setController(controller); // Inyectar el controlador
            }

            AnchorPane root = loader.load(); // Cargar el FXML

            // Crear la nueva escena con la vista cargada
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Ventana");

            // Mostrar la ventana
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            // Aquí podrías agregar un manejo de errores más avanzado, como mostrar un diálogo de error.
        }
    }

    // Método para mostrar la ventana de "Student" (Ejemplo específico)
    public void showStudentWindow() {
        StudentController studentController = new StudentController();
        showWindow("/Fxml/Student/Student.fxml", studentController);
    }

    // Método para mostrar la ventana de "Profesor" (Ejemplo específico)
    public void showProfessorWindow() {
        ProfessorController professorController = new ProfessorController();
        showWindow("/Fxml/Professor/Professor.fxml", professorController);
    }

    // Método para mostrar la ventana principal (ejemplo genérico)
    /**
     * public void showMainWindow() {
        MainController mainController = new MainController();
        showWindow("/Fxml/Main/Main.fxml", mainController);
    }
     */
    public void showLoginWindow(){
        LoginController loginController = new LoginController();
        showWindow("/Fxml/Main/Login.fxml", loginController);
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
