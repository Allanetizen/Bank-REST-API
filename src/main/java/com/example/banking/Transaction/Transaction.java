package com.example.banking.Transaction;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
        uniqueConstraints = {
               /* @UniqueConstraint(columnNames = "transactionCode"),*/
        }
)
public class Transaction {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    private Long Id;
    private String transactionCode;
    private String accountNumber;
    private String action; // "CREDIT","DEBIT"
    private float amount;

    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false, insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Transaction() {
    }

    public Transaction(Long id, String transactionCode, String accountNumber, String action, float amount) {
        Id = id;
        this.transactionCode = transactionCode;
        this.accountNumber = accountNumber;
        this.action = action;
        this.amount = amount;
    }

    public Transaction(String transactionCode, String accountNumber, String action, float amount) {
        this.transactionCode = transactionCode;
        this.accountNumber = accountNumber;
        this.action = action;
        this.amount = amount;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "Id=" + Id +
                ", transactionCode='" + transactionCode + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", action='" + action + '\'' +
                ", amount=" + amount +
                '}';
    }
}
