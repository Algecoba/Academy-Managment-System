package com.acb.ams.Models;

public class TeacherSubject {
    private int proId;
    private int asigId;

    // Relación con Persona (Profesor)
    private Person profesor;

    // Relación con Asignatura
    private Subject asignatura;

    public TeacherSubject(int proId, int asigId, Person profesor, Subject asignatura) {
        this.proId = proId;
        this.asigId = asigId;
        this.profesor = profesor;
        this.asignatura = asignatura;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getAsigId() {
        return asigId;
    }

    public void setAsigId(int asigId) {
        this.asigId = asigId;
    }

    public Person getProfesor() {
        return profesor;
    }

    public void setProfesor(Person profesor) {
        this.profesor = profesor;
    }

    public Subject getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Subject asignatura) {
        this.asignatura = asignatura;
    }

    // Constructor, Getters and Setters
    
}
