package com.acb.ams.Models;

import java.time.LocalDate;


public class User {

    private int usuId;
    private int perId;
    private String usuNomUsuario;
    private String usuContrasenia;
    private LocalDate usuFcreacion;
    private LocalDate usuFultimoIngreso;

    // Relación con Persona
    private Person persona;

    public User(int usuId, int perId, String usuNomUsuario, String usuContrasenia, LocalDate usuFcreacion,
            LocalDate usuFultimoIngreso, Person persona) {
        this.usuId = usuId;
        this.perId = perId;
        this.usuNomUsuario = usuNomUsuario;
        this.usuContrasenia = usuContrasenia;
        this.usuFcreacion = usuFcreacion;
        this.usuFultimoIngreso = usuFultimoIngreso;
        this.persona = persona;
    }

    public int getUsuId() {
        return usuId;
    }

    public void setUsuId(int usuId) {
        this.usuId = usuId;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getUsuNomUsuario() {
        return usuNomUsuario;
    }

    public void setUsuNomUsuario(String usuNomUsuario) {
        this.usuNomUsuario = usuNomUsuario;
    }

    public String getUsuContrasenia() {
        return usuContrasenia;
    }

    public void setUsuContrasenia(String usuContrasenia) {
        this.usuContrasenia = usuContrasenia;
    }

    public LocalDate getUsuFcreacion() {
        return usuFcreacion;
    }

    public void setUsuFcreacion(LocalDate usuFcreacion) {
        this.usuFcreacion = usuFcreacion;
    }

    public LocalDate getUsuFultimoIngreso() {
        return usuFultimoIngreso;
    }

    public void setUsuFultimoIngreso(LocalDate usuFultimoIngreso) {
        this.usuFultimoIngreso = usuFultimoIngreso;
    }

    public Person getPersona() {
        return persona;
    }

    public void setPersona(Person persona) {
        this.persona = persona;
    }

    // Constructor, Getters and Setters

    
}
