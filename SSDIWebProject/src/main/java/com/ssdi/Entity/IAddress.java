package com.ssdi.Entity;

/**
 * Created by prayas on 3/25/2017.
 */
public interface IAddress {

    String getStreetAddress();
    String getApartment();
    String getCity();
    String getState();
    String getZip();
    String getEmail();
    void setStreetAddress(String streetAddress);
    void setApartment(String apartment);
    void setCity(String city);
    void setState(String state);
    void setZip(String zip);
    void setEmail(String email);

}
