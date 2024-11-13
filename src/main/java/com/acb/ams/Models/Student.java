package com.acb.ams.Models;



/**
 *
 * @author Jorge
 */
//Clase referente a Estudiante
public class Student extends Person{

    private Person guardian1;
    private Person guardian2;
    private Course course;

    public Student(String names, String surnames, int identification, int id, Person guardian1, Person guardian2,
            Course course) {
        super(names, surnames, identification, id);
        this.guardian1 = guardian1;
        this.guardian2 = guardian2;
        this.course = course;
    }

    public Student(String names, String surnames, int identification, int id) {
        super(names, surnames, identification, id);
    }

    public Person getGuardian1() {
        return guardian1;
    }

    public void setGuardian1(Person guardian1) {
        this.guardian1 = guardian1;
    }

    public Person getGuardian2() {
        return guardian2;
    }

    public void setGuardian2(Person guardian2) {
        this.guardian2 = guardian2;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", names=" + names + ", surnames=" + surnames + ", guardian1=" + guardian1
                + ", identification=" + identification + ", guardian2=" + guardian2 + ", course=" + course + "]";
    }

   

    

   

    


    

    
    
    
}
