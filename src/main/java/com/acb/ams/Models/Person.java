
package com.acb.ams.Models;

import java.lang.classfile.constantpool.DoubleEntry;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Jorge
 */
public class Person {

    private int perId;
    private String perEmail;
    private Integer perCodigo;
    private String perNombres;
    private String perApellidos;
    private String perEstado;
    private String perTipo;
    private Integer acudId1;
    private Integer acudId2;
    private Integer curId;
    private String tipoIdent;
    // Getters and setters

    // Relación con Acudientes
    private Person acudiente1;
    private Person acudiente2;

    // Relación con Curso
    private Course curso;

    // Relación con Usuarios
    private User user;

    // private List<Activities> actividades;
    private String activitie;
    private double qualification;

    public Person(String perNombres, String activitie, double qualification) {
        this.perNombres = perNombres;
        this.activitie = activitie;
        this.qualification = qualification;
    }

    // El constructor instancia como nulo las propiedades finales porque no sabia
    // que hacian

    public Person(int codigo, String tipoIdent, String nombres, String apellidos, String estado, String rol){
        this.perCodigo = codigo;
        this.tipoIdent = tipoIdent;
        this.perNombres = nombres;
        this.perApellidos = apellidos;
        this.perEstado = estado;
        this.perTipo = rol;
    }

    public Person(String Nombres, String Apellidos, Integer CursoID, Integer ID) {
        this.perNombres = Nombres;
        this.perApellidos = Apellidos;
        this.curId = CursoID;
        this.perId = ID;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail;
    }

    public Integer getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(Integer perCodigo) {
        this.perCodigo = perCodigo;
    }

    

    public String getPerNombres() {
        return perNombres;
    }

    public void setPerNombres(String perNombres) {
        this.perNombres = perNombres;
    }

    public String getPerApellidos() {
        return perApellidos;
    }

    public void setPerApellidos(String perApellidos) {
        this.perApellidos = perApellidos;
    }

    public String getTipoIdent() {
        return tipoIdent;
    }

    public void setTipoIdent(String tipoIdent) {
        this.tipoIdent = tipoIdent;
    }

    public String getActivitie() {
        return activitie;
    }

    public void setActivitie(String activitie) {
        this.activitie = activitie;
    }

    public double getQualification() {
        return qualification;
    }

    public void setQualification(double qualification) {
        this.qualification = qualification;
    }

    public String getPerEstado() {
        return perEstado;
    }

    public void setPerEstado(String perEstado) {
        this.perEstado = perEstado;
    }

    public String getPerTipo() {
        return perTipo;
    }

    public void setPerTipo(String perTipo) {
        this.perTipo = perTipo;
    }

    public Integer getAcudId1() {
        return acudId1;
    }

    public void setAcudId1(Integer acudId1) {
        this.acudId1 = acudId1;
    }

    public Integer getAcudId2() {
        return acudId2;
    }

    public void setAcudId2(Integer acudId2) {
        this.acudId2 = acudId2;
    }

    public Integer getCurId() {
        return curId;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public Person getAcudiente1() {
        return acudiente1;
    }

    public void setAcudiente1(Person acudiente1) {
        this.acudiente1 = acudiente1;
    }

    public Person getAcudiente2() {
        return acudiente2;
    }

    public void setAcudiente2(Person acudiente2) {
        this.acudiente2 = acudiente2;
    }

    public Course getCurso() {
        return curso;
    }

    public void setCurso(Course curso) {
        this.curso = curso;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
