package com.ssdi.Entity;

import java.util.Date;

/**
 * Created by Admin on 4/6/2017.
 */
public interface ITransaction {
    public void setTransaction_id(int transaction_id);
    public void setProduct_id(int product_id);
    public void setPayment_id(int payment_id);
    public void setAddress_id(int address_id);
    public void setEmail_rentee(String email_rentee);
    public void setEmail_renter(String email_renter);
    public void setEnd_date(String end_date);
    public void setStart_date(String start_date);
    public int getPayment_id();
    public int getAddress_id();
    public String getEmail_rentee();
    public String getEmail_renter();
    public int getProduct_id();
    public int getTransaction_id();
    public String getEnd_date();
    public String getStart_date();

}
