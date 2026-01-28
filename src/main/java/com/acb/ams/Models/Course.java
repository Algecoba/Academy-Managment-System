package com.acb.ams.Models;


//Clase referente a Curso
public class Course {
    
    private int curId;
    private String curGrado;
    private int penId;

    // Relación con Pensum
    private Curriculum pensum;



    public Course(String nombre){
        this.curGrado = nombre;
    }

    public int getCurId() {
        return curId;
    }

    public void setCurId(int curId) {
        this.curId = curId;
    }

    public String getCurGrado() {
        return curGrado;
    }

    public void setCurGrado(String curGrado) {
        this.curGrado = curGrado;
    }

    public int getPenId() {
        return penId;
    }

    public void setPenId(int penId) {
        this.penId = penId;
    }

    public Curriculum getPensum() {
        return pensum;
    }

    public void setPensum(Curriculum pensum) {
        this.pensum = pensum;
    }

    @Override
    public String toString() {
        return "Course: " + curGrado;
    }

    // Constructor, Getters and Setters

    

}
