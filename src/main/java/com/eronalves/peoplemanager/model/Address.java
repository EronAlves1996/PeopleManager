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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((streetName == null) ? 0 : streetName.hashCode());
        result = prime * result + ((addressNumber == null) ? 0 : addressNumber.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (streetName == null) {
            if (other.streetName != null)
                return false;
        } else if (!streetName.equals(other.streetName))
            return false;
        if (addressNumber == null) {
            if (other.addressNumber != null)
                return false;
        } else if (!addressNumber.equals(other.addressNumber))
            return false;
        return true;
    }

}
