package com.acb.ams.Models;

import com.acb.ams.Views.ViewFactory;


//Clase model...Intermediaria ?? 
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    //Constructor
    private Model(){
        this.viewFactory = new ViewFactory();
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

}
