package com.ssdi.Entity;
import java.util.Date;
/**
 * Created by Admin on 4/6/2017.
 */
public class Transaction implements ITransaction{
    private int transaction_id;
    private int product_id;
    private int payment_id;
    private int address_id;
    private String email_renter;
    private String email_rentee;
    private Date start_date = new Date();
    private Date end_date = new Date();



    public void setTransaction_id(int transaction_id){this.transaction_id=transaction_id;}
    public void setProduct_id(int product_id){this.product_id=product_id;}

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public void setEmail_rentee(String email_rentee) {
        this.email_rentee = email_rentee;
    }

    public void setEmail_renter(String email_renter) {
        this.email_renter = email_renter;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public int getAddress_id() {
        return address_id;
    }

    public String getEmail_rentee() {
        return email_rentee;
    }

    public String getEmail_renter() {
        return email_renter;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Date getStart_date() {
        return start_date;
    }
}
