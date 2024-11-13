
package com.acb.ams.Models;

/**
 *
 * @author Jorge
 */
public class Person {

    protected int id;
    protected String names;
    protected String surnames;
    protected int identification;

    public Person(String names, String surnames, int identification, int id) {
        this.names = names;
        this.surnames = surnames;
        this.identification = identification;
        this.id = id;
    }


    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public int getIdentification() {
        return identification;
    }

    public void setIdentification(int identification) {
        this.identification = identification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", names=" + names + ", surnames=" + surnames + ", identification=" + identification + '}';
    }

    
    

}
