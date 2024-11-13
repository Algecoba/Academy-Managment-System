package com.acb.ams.Models;

import java.time.LocalDate;

public class Attendance {
    private int id;
    private LocalDate date;
    private char status;

    public Attendance(int id, LocalDate date, char status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance: \n" +
               "ID: " + id + "\n" +
               "Date: " + date + "\n" +
               "Status: " + status + "\n";
    }

    
    
}
