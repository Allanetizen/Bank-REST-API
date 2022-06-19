package com.example.banking.Customer;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "CustomerId")
        }
)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    private Long Id;
    private String Name;
    private String CustomerId;
    private String PIN;

    private String email;
    private String firstName;
    private String lastName;

    @CreationTimestamp
    @Column(name="timestamp", nullable = false, updatable = false, insertable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;


    public Customer() {
    }

    public Customer(Long id, String name, String customerId, String PIN, String email, String firstName, String lastName) {
        Id = id;
        Name = name;
        CustomerId = customerId;
        this.PIN = PIN;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer(String name, String customerId, String PIN, String email, String firstName, String lastName) {
        Name = name;
        CustomerId = customerId;
        this.PIN = PIN;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                ", PIN='" + PIN + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
