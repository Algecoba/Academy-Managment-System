package com.acb.ams.Models;

import java.util.List;

//Clase referente a Materia
public class Subject {

    private int id;
    private String name;
    private List<GradingPeriod> gradingPeriod;

    public Subject(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GradingPeriod> getGradingPeriod() {
        return gradingPeriod;
    }

    public void setGradingPeriod(List<GradingPeriod> gradingPeriod) {
        this.gradingPeriod = gradingPeriod;
    }

    @Override
    public String toString() {
        return name;
    }

}
