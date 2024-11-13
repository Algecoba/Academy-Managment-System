package com.acb.ams.Models;

import java.util.List;

//Clase referente a Curso
public class Course {
    
    private int id;
    private String name;
    private List<Student> student;
    private Curriculum curriculums;
    
    public Course(int id, String name) {
        this.id = id;
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



    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }
    
    public Curriculum getCurriculums() {
        return curriculums;
    }

    public void setCurriculums(Curriculum curriculums) {
        this.curriculums = curriculums;
    }

    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", student=" + student + ", curriculums=" + curriculums + "]";
    }

    


}
