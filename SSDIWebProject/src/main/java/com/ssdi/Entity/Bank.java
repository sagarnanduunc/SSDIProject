package com.ssdi.Entity;

/**
 * Created by prayas on 3/25/2017.
 */
public class Bank implements IBank {

    String bankName;
    long accountNumber;
    String accountHolderName;
    long routingNumber;
    String email;

    public Bank(String bankName, long accountNumber, String accountHolderName, long routingNumber){
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.routingNumber = routingNumber;
        this.email = getEmail();
    }

    public Bank(){}

    @Override
    public String getBankName() {
        return this.bankName;
    }

    @Override
    public long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public String getAccountHolderName() {
        return this.accountHolderName;
    }

    @Override
    public long getRoutingNumber() {
        return this.routingNumber;
    }

    @Override
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    @Override
    public void setRoutingNumber(long routingNumber) {
        this.routingNumber = routingNumber;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
