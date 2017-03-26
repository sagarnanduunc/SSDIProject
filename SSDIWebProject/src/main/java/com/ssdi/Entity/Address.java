package com.ssdi.Entity;

/**
 * Created by prayas on 3/25/2017.
 */
public class Address implements IAddress {

    String streetAddress;
    String apartment;
    String city;
    String state;
    String zip;
    String email;

    public Address(String streetAddress, String apartment, String city, String state, String zip, String email){
        this.streetAddress = streetAddress;
        this.apartment = apartment;
        this.city =city;
        this.state = state;
        this.zip = zip;
        this.email = email;
    }

    public Address(){}

    @Override
    public String getStreetAddress() {
        return this.streetAddress;
    }

    @Override
    public String getApartment() {
        return this.apartment;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String getZip() {
        return this.zip;
    }

    @Override
    public  String getEmail() { return this.email; }

    @Override
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    @Override
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
