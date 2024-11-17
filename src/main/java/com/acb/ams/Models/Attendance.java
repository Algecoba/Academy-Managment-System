package com.acb.ams.Models;

import java.time.LocalDate;


public class Attendance {
    private int asisId;
    private int estId;
    private int asigId;
    private LocalDate asisFecha;
    private String asisEstado;

    // Relación con Estudiante (Persona)
    private Person estudiante;

    // Relación con Asignatura
    private Subject asignatura;

    public Attendance(int asisId, int estId, int asigId, LocalDate asisFecha, String asisEstado, Person estudiante,
            Subject asignatura) {
        this.asisId = asisId;
        this.estId = estId;
        this.asigId = asigId;
        this.asisFecha = asisFecha;
        this.asisEstado = asisEstado;
        this.estudiante = estudiante;
        this.asignatura = asignatura;
    }

    public int getAsisId() {
        return asisId;
    }

    public void setAsisId(int asisId) {
        this.asisId = asisId;
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

    public LocalDate getAsisFecha() {
        return asisFecha;
    }

    public void setAsisFecha(LocalDate asisFecha) {
        this.asisFecha = asisFecha;
    }

    public String getAsisEstado() {
        return asisEstado;
    }

    public void setAsisEstado(String asisEstado) {
        this.asisEstado = asisEstado;
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

    // Constructor, Getters and Setters
    
}
