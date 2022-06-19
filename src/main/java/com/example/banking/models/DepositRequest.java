package com.example.banking.models;

public class DepositRequest {
    private String accountNumber;
    private float amount;

    public DepositRequest() {
    }

    public DepositRequest(String accountNumber, float amount) {
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DepositRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
