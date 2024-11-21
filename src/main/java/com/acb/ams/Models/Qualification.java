package com.acb.ams.Models;

public class Qualification {
    private int notId;
    private String notDesempenio;
    private double notDefinitiva;
    private int asigId;
    private int corId;
    private int estId;

    // Relación con Asignatura
    private Subject asignatura;

    // Relación con Corte
    private GradingPeriod corte;

    // Relación con Estudiante (Persona)
    private Person estudiante;

    public Qualification(Double notDefinitiva) {
        this.notDefinitiva = notDefinitiva;
    }

    public int getNotId() {
        return notId;
    }

    public void setNotId(int notId) {
        this.notId = notId;
    }

    public String getNotDesempenio() {
        return notDesempenio;
    }

    public void setNotDesempenio(String notDesempenio) {
        this.notDesempenio = notDesempenio;
    }

    public double getNotDefinitiva() {
        return notDefinitiva;
    }

    public void setNotDefinitiva(double notDefinitiva) {
        this.notDefinitiva = notDefinitiva;
    }

    public int getAsigId() {
        return asigId;
    }

    public void setAsigId(int asigId) {
        this.asigId = asigId;
    }

    public int getCorId() {
        return corId;
    }

    public void setCorId(int corId) {
        this.corId = corId;
    }

    public int getEstId() {
        return estId;
    }

    public void setEstId(int estId) {
        this.estId = estId;
    }

    public Subject getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Subject asignatura) {
        this.asignatura = asignatura;
    }

    public GradingPeriod getCorte() {
        return corte;
    }

    public void setCorte(GradingPeriod corte) {
        this.corte = corte;
    }

    public Person getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Person estudiante) {
        this.estudiante = estudiante;
    }

    @Override
    public String toString() {
        return "" + notDefinitiva;
    }

}
