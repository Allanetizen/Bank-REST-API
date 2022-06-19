package com.example.banking.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Random;

@Component
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
        /*List<Customer> customers = List.of(new Customer(1L,"XXX", "yyy","zzz"));
        return customers;*/
    }

    public Customer addCustomer(Customer customer) {
        //customer.setId(1L);
        return this.customerRepository.save(customer);
    }
}
