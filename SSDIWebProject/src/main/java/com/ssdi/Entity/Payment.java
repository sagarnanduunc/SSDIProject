package com.ssdi.Entity;

/**
 * Created by Admin on 4/4/2017.
 */
public class Payment implements IPayment{
    private int id;
    private String card_no;
    private String security_code;
    private String name;
    private String zip;
    private int expmonth;
    private int expyear;
    private String email;

    public Payment(){}

    public Payment(String card_no,String security_code, String name, String zip, int expmonth, int expyear, String email)
    {
        this.email=email;
        this.card_no=card_no;
        this.security_code=security_code;
        this.name=name;
        this.zip=zip;
        this.expmonth=expmonth;
        this.expyear=expyear;
    }
    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getCard_no(){return card_no;}
    public String getSecurity_code(){return security_code;}
    public String getName(){return name;}
    public String getZip(){return zip;}
    public String getEmail(){return email;}
    public  int getExpmonth(){return expmonth;}
    public int getExpyear(){return expyear;}
    public void setCard_no(String card_no){this.card_no=card_no;}
    public void setSecurity_code(String security_code){this.security_code=security_code;}
    public void setName(String name){this.name=name;}
    public void setZip(String zip){this.zip=zip;}
    public void setMonth(int expmonth){this.expmonth=expmonth;}
    public void setYear(int expyear){this.expyear=expyear;}
    public void setEmail(String email){this.email=email;}

}
