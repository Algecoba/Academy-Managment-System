package com.acb.ams.Models;



import com.acb.ams.Data.DatabaseConnector;
import com.acb.ams.Views.ViewFactory;



//Clase model...Intermediaria ?? 
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private DatabaseConnector database;
    // private static DatabaseConnector database;

    // Constructor
    private Model() {
        this.viewFactory = new ViewFactory();
        this.database = new DatabaseConnector();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseConnector getDataBase() {
        return database;
    }

}
