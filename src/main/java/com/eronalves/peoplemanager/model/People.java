package com.eronalves.peoplemanager.model;

import java.util.Date;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class People {

    @Id
    private int id;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Embedded
    private Address adress;

    public People(int id, String name, Date birthDate, Address adress) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.adress = adress;
    }

    public People(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    
}

