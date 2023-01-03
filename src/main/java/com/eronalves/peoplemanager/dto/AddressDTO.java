package com.eronalves.peoplemanager.dto;

public class AddressDTO {

    private String streetName;
    private String zipCode;
    private String addressNumber;
    private String city;
    private boolean mainAddress;

    public AddressDTO(String streetName, String zipCode, String addressNumber, String city, boolean mainAddress) {
        this.streetName = streetName;
        this.zipCode = zipCode;
        this.addressNumber = addressNumber;
        this.city = city;
        this.mainAddress = mainAddress;
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

    public boolean isMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

}
