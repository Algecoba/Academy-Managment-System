package com.acb.ams.Views;


//import javax.sound.midi.VoiceStatus;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactory {

    // Implementación del patrón Singleton para garantizar que solo haya una instancia
    private static ViewFactory instance;

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
     */
    private void showWindow(String fxmlPath, Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load(); // Cargar el FXML
    
            Scene scene = new Scene(root);
    
            // Si se pasa un stage, se usa, de lo contrario se crea uno nuevo
            if (stage == null) {
                stage = new Stage();
            }
            stage.setScene(scene);
            stage.setTitle("Ventana");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    


    // Método para mostrar la ventana de "Student" 
    public void showStudentWindow(Stage stage) {
        showWindow("/Fxml/Student/Student.fxml", stage);
    }

    // Método para mostrar la ventana de "Profesor" 
    public void showProfessorWindow(Stage stage) {
        showWindow("/Fxml/Professor/Professor.fxml", stage);
    }

    //Metodo para mostrar la ventana de "Login" 
    public void showLoginWindow(Stage stage){
        showWindow("/Fxml/Login/Login.fxml", stage);
    }

    //Metodo para mostrar la ventana de "Admin"
    public void showAdminWindow(Stage stage){
        showWindow("/Fxml/Admin/Admin.fxml", stage);
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
