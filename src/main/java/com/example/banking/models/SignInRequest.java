package com.example.banking.models;

public class SignInRequest {
    private String ID;
    private String pin;

    public SignInRequest() {
    }

    public SignInRequest(String ID, String pin) {
        this.ID = ID;
        this.pin = pin;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "SignInRequest{" +
                "ID='" + ID + '\'' +
                ", pin='" + pin + '\'' +
                '}';
    }
}
