package com.example.banking.models;


public class SignUpRequest {
    private String customerName;
    private String ID;

    public SignUpRequest() {

    }

    public SignUpRequest(String customerName, String ID) {
        this.customerName = customerName;
        this.ID = ID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "customerName='" + customerName + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
