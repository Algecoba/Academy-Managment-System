package com.acb.ams.Models;


import java.time.LocalDate;

//Clase referente a Actividades
public class Activities {

    private int id;
    private String name;
    private double grade;
    private LocalDate submissionDate;

    public Activities(int id, String name, double grade, LocalDate submissionDate) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.submissionDate = submissionDate;

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

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }


    @Override
    public String toString() {
        return "Activities: \n" +
               "ID: " + id + "\n" +
               "Name: " + name + "\n" +
               "Grade: " + grade + "\n" +
               "Submission Date: " + submissionDate + "\n";
    }

    

}
