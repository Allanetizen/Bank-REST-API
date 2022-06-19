package com.example.banking.Account;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "customerId"),
                @UniqueConstraint(columnNames = "accountNumber")
        }
)
public class Account {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "account_sequence",
            sequenceName = "account_sequence",
            allocationSize = 1
    )
    private Long Id;
    private String customerId;
    private String accountNumber;
    private float balance;

    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false, insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;

    public Account() {
    }

    public Account(Long id, String customerId, String accountNumber, float balance) {
        Id = id;
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Account(String customerId, String accountNumber, float balance) {
        this.customerId = customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", customerId='" + customerId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", balance=" + balance +
                '}';
    }
}
