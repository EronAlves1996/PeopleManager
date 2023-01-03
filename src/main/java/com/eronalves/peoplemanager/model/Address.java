package com.eronalves.peoplemanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String streetName;
    private String zipCode;
    private String addressNumber;
    private String city;
    private boolean mainAddress;

    public Address(String streetName, String zipCode, String addressNumber, String city, boolean mainAddress) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.addressNumber = addressNumber;
        this.city = city;
        this.mainAddress = mainAddress;
    }

    public Address() {
    }

    public boolean isMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Address [id=" + id + ", streetName=" + streetName + ", zipCode=" + zipCode + ", addressNumber="
                + addressNumber + ", city=" + city + ", mainAddress=" + mainAddress + "]";
    }

    
}
