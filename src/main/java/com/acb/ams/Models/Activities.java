package com.acb.ams.Models;

import java.sql.Date;

import javafx.beans.property.StringProperty;

//Clase referente a Actividades
public class Activities {
    private int actId;
    private String actNombre;
    private java.util.Date actFecha;
    private int estId;
    private int asigId;
    private int critId;
    private double actNota;

    // Relación con Estudiante (Persona)
    private Person estudiante;

    // Relación con Asignatura
    private String asignatura;

    // Relación con Criterio
    private String criterio;

    public Activities(String asignatura, String actividad, java.util.Date actFecha, double actNota, String criterio) {
        this.asignatura = asignatura;
        this.actNombre = actividad;
        this.actFecha = actFecha;
        this.actNota = actNota;
        this.criterio = criterio;
    }

    public Activities(String actNombre) {
        this.actNombre = actNombre;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    
    
    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public void setActFecha(Date actFecha) {
        this.actFecha = actFecha;
    }

    public int getEstId() {
        return estId;
    }

    public void setEstId(int estId) {
        this.estId = estId;
    }

    public int getAsigId() {
        return asigId;
    }

    public void setAsigId(int asigId) {
        this.asigId = asigId;
    }

    public int getCritId() {
        return critId;
    }

    public void setCritId(int critId) {
        this.critId = critId;
    }

    public double getActNota() {
        return actNota;
    }

    public void setActNota(double actNota) {
        this.actNota = actNota;
    }

    public Person getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Person estudiante) {
        this.estudiante = estudiante;
    }


    @Override
    public String toString() {
        return "Activities [actNombre=" + actNombre + "]";
    }

    public java.util.Date getActFecha() {
        return actFecha;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }

    // Constructor, Getters and Setters

}
