package com.ssdi.Entity;

/**
 * Created by Admin on 4/4/2017.
 */
public interface IPayment {
    public int getId();
    public void setId(int id);
    public String getCard_no();
    public String getSecurity_code();
    public String getName();
    public String getZip();
    public String getEmail();
    public  int getExpmonth();
    public int getExpyear();
    public void setCard_no(String card_no);
    public void setSecurity_code(String security_code);
    public void setName(String name);
    public void setZip(String zip);
    public void setMonth(int expmonth);
    public void setYear(int expyear);
    public void setEmail(String email);
}
