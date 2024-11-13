package com.acb.ams.Models;


//Clase referente a Profesor
public class Teacher extends Person{
    
    private Subject subjects;

    //Constructor del que extiende
    public Teacher(String names, String surnames, int identification, int id) {
        super(names, surnames, identification, id);

    }

    //Construccor propio
    public Teacher(String names, String surnames, int identification, int id, Subject subjects) {
        super(names, surnames, identification, id);
        this.subjects = subjects;
    }

    public Subject getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject subjects) {
        this.subjects = subjects;
    }



    @Override
    public String toString() {
        return "Teacher: \n" +
               "ID: " + id + "\n" +
               "Names: " + names + "\n" +
               "Surnames: " + surnames + "\n" +
               "Subjects: " + subjects + "\n" +
               "Identification: " + identification + "\n";
    }

    
}
