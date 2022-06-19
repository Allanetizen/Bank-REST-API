package com.example.banking.models;

public class FundTransferRequest {

    public String creditAccount;
    public String debitAccount;
    public float amount;

    public FundTransferRequest() {
    }

    public FundTransferRequest(String creditAccount, String debitAccount, float amount) {
        this.creditAccount = creditAccount;
        this.debitAccount = debitAccount;
        this.amount = amount;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
