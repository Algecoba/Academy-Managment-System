package com.acb.ams.Models;

public class Grades {
    private int id;
    private String performance;
    private double finalGrade;
    private Subject subject;
    private GradingPeriod gradingPeriod;
    private Student student;

    public Grades(int id, String performance, double finalGrade, Subject subject, GradingPeriod gradingPeriod,
            Student student) {
        this.id = id;
        this.performance = performance;
        this.finalGrade = finalGrade;
        this.subject = subject;
        this.gradingPeriod = gradingPeriod;
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public double getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(double finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public GradingPeriod getGradingPeriod() {
        return gradingPeriod;
    }

    public void setGradingPeriod(GradingPeriod gradingPeriod) {
        this.gradingPeriod = gradingPeriod;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    



    

}
