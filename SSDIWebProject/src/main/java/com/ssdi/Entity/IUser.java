package com.ssdi.Entity;

/**
 * Created by prayas on 3/20/2017.
 */
public interface IUser {
    String getFirstName();
    void setFirstName(String firstName);
    String getLastName();
    void setLastName(String lastName);
    String getEmail();
    void setEmail(String email);
    String getPassword();
    void setPassword(String password);
}
