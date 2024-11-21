package com.acb.ams.Models;

public class Criterion {
    private int critId;
    private String critNombre;
    private double critPeso;
    private int asigId;

    // Relación con Asignatura
    private Subject asignatura;

    public Criterion(int critId, String critNombre, double critPeso, int asigId, Subject asignatura) {
        this.critId = critId;
        this.critNombre = critNombre;
        this.critPeso = critPeso;
        this.asigId = asigId;
        this.asignatura = asignatura;
    }

    

    public Criterion(String critNombre) {
        this.critNombre = critNombre;
    }



    public int getCritId() {
        return critId;
    }

    public void setCritId(int critId) {
        this.critId = critId;
    }

    public String getCritNombre() {
        return critNombre;
    }

    public void setCritNombre(String critNombre) {
        this.critNombre = critNombre;
    }

    public double getCritPeso() {
        return critPeso;
    }

    public void setCritPeso(double critPeso) {
        this.critPeso = critPeso;
    }

    public int getAsigId() {
        return asigId;
    }

    public void setAsigId(int asigId) {
        this.asigId = asigId;
    }

    public Subject getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Subject asignatura) {
        this.asignatura = asignatura;
    }

    

}
