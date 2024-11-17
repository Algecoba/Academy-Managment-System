package com.acb.ams.Models;

public class Contact {
    private int contId;
    private int contTelefono;
    private String contCorreo;
    private String contCiudad;
    private String contDepartamento;
    private String contDireccion;
    private String contEstado;
    private String contTipoContacto;
    private int perId;

    // Relación con Persona
    private Person persona;

    public Contact(int contId, int contTelefono, String contCorreo, String contCiudad, String contDepartamento,
            String contDireccion, String contEstado, String contTipoContacto, int perId, Person persona) {
        this.contId = contId;
        this.contTelefono = contTelefono;
        this.contCorreo = contCorreo;
        this.contCiudad = contCiudad;
        this.contDepartamento = contDepartamento;
        this.contDireccion = contDireccion;
        this.contEstado = contEstado;
        this.contTipoContacto = contTipoContacto;
        this.perId = perId;
        this.persona = persona;
    }

    public int getContId() {
        return contId;
    }

    public void setContId(int contId) {
        this.contId = contId;
    }

    public int getContTelefono() {
        return contTelefono;
    }

    public void setContTelefono(int contTelefono) {
        this.contTelefono = contTelefono;
    }

    public String getContCorreo() {
        return contCorreo;
    }

    public void setContCorreo(String contCorreo) {
        this.contCorreo = contCorreo;
    }

    public String getContCiudad() {
        return contCiudad;
    }

    public void setContCiudad(String contCiudad) {
        this.contCiudad = contCiudad;
    }

    public String getContDepartamento() {
        return contDepartamento;
    }

    public void setContDepartamento(String contDepartamento) {
        this.contDepartamento = contDepartamento;
    }

    public String getContDireccion() {
        return contDireccion;
    }

    public void setContDireccion(String contDireccion) {
        this.contDireccion = contDireccion;
    }

    public String getContEstado() {
        return contEstado;
    }

    public void setContEstado(String contEstado) {
        this.contEstado = contEstado;
    }

    public String getContTipoContacto() {
        return contTipoContacto;
    }

    public void setContTipoContacto(String contTipoContacto) {
        this.contTipoContacto = contTipoContacto;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public Person getPersona() {
        return persona;
    }

    public void setPersona(Person persona) {
        this.persona = persona;
    }

    
}
