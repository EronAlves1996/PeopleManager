package com.eronalves.peoplemanager.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

    private String streetName;
    private String zipCode;
    private String addressNumber;
    private String city;

    public Address(String streetName, String zipCode, String addressNumber, String city) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.addressNumber = addressNumber;
        this.city = city;
    }

    public Address() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(String addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    

    
}
