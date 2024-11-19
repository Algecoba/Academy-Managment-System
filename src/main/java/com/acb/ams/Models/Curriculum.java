package com.acb.ams.Models;

import java.util.List;

public class Curriculum {
    private int penId;
    private String penCodigo;
    private int penAnioPensum;
    private int penCantasignaturas;

    // Relación con Asignaturas
    private List<Subject> asignaturas;

    public Curriculum(int penId, String penCodigo, int penAnioPensum, int penCantasignaturas,
            List<Subject> asignaturas) {
        this.penId = penId;
        this.penCodigo = penCodigo;
        this.penAnioPensum = penAnioPensum;
        this.penCantasignaturas = penCantasignaturas;
        this.asignaturas = asignaturas;
    }

    public int getPenId() {
        return penId;
    }

    public void setPenId(int penId) {
        this.penId = penId;
    }

    public String getPenCodigo() {
        return penCodigo;
    }

    public void setPenCodigo(String penCodigo) {
        this.penCodigo = penCodigo;
    }

    public int getPenAnioPensum() {
        return penAnioPensum;
    }

    public void setPenAnioPensum(int penAnioPensum) {
        this.penAnioPensum = penAnioPensum;
    }

    public int getPenCantasignaturas() {
        return penCantasignaturas;
    }

    public void setPenCantasignaturas(int penCantasignaturas) {
        this.penCantasignaturas = penCantasignaturas;
    }

    public List<Subject> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(List<Subject> asignaturas) {
        this.asignaturas = asignaturas;
    }

    // Constructor, Getters and Setters
    
}
