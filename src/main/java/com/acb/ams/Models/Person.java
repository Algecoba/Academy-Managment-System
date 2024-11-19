
package com.acb.ams.Models;

import java.lang.classfile.constantpool.DoubleEntry;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private final StringProperty perNombres;
    private StringProperty perApellidos;
    private String perEstado;
    private String perTipo;
    private Integer acudId1;
    private Integer acudId2;
    private Integer curId;

    // Getters and setters

    // Relación con Acudientes
    private Person acudiente1;
    private Person acudiente2;

    // Relación con Curso
    private Course curso;

    // Relación con Usuarios
    private User user;

    // private List<Activities> actividades;
    private final StringProperty activitie;
    private final DoubleProperty qualification;

    public Person(String perNombres, String activitie, double qualification) {
        this.perNombres = new SimpleStringProperty(perNombres);
        this.activitie = new SimpleStringProperty(activitie);
        this.qualification = new SimpleDoubleProperty(qualification);
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
        return perNombres.get();
    }

    public void setPerNombres(String perNombres) {
        this.perNombres.set(perNombres);
    }

    public StringProperty getPerApellidos() {
        return perApellidos;
    }

    public void setPerApellidos(StringProperty perApellidos) {
        this.perApellidos = perApellidos;
    }

    public String getActivitie() {
        return activitie.get();
    }

    public void setActivitie(String activitie) {
        this.activitie.set(activitie);
    }

    public Double getQualification() {
        return qualification.get();
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
