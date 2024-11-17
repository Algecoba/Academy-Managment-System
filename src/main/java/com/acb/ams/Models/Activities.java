package com.acb.ams.Models;


import java.time.LocalDate;


//Clase referente a Actividades
public class Activities {
    private int actId;
    private String actNombre;
    private LocalDate actFecha;
    private int estId;
    private int asigId;
    private int critId;
    private double actNota;

    // Relación con Estudiante (Persona)
    private Person estudiante;

    // Relación con Asignatura
    private Subject asignatura;

    // Relación con Criterio
    private Criterion criterio;

    public Activities(String actNombre, double actNota, LocalDate actFecha){
        this.actNombre = actNombre;
        this.actNota = actNota;
        this.actFecha = actFecha;
    }

    public Activities(int actId, String actNombre, LocalDate actFecha, int estId, int asigId, int critId, double actNota,
            Person estudiante, Subject asignatura, Criterion criterio) {
        this.actId = actId;
        this.actNombre = actNombre;
        this.actFecha = actFecha;
        this.estId = estId;
        this.asigId = asigId;
        this.critId = critId;
        this.actNota = actNota;
        this.estudiante = estudiante;
        this.asignatura = asignatura;
        this.criterio = criterio;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }

    public LocalDate getActFecha() {
        return actFecha;
    }

    public void setActFecha(LocalDate actFecha) {
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

    public Subject getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Subject asignatura) {
        this.asignatura = asignatura;
    }

    public Criterion getCriterio() {
        return criterio;
    }

    public void setCriterio(Criterion criterio) {
        this.criterio = criterio;
    }

    // Constructor, Getters and Setters
    

}
