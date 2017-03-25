package com.ssdi.Entity;

/**
 * Created by prayas on 3/25/2017.
 */
public interface IBank {

    String getBankName();
    long getAccountNumber();
    String getAccountHolderName();
    long getRoutingNumber();
    String getEmail();
    void setBankName(String bankName);
    void setAccountNumber(long accountNumber);
    void setAccountHolderName(String accountHolderName);
    void setRoutingNumber(long routingNumber);
    void setEmail(String email);

}
