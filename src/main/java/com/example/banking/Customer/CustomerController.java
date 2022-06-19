package com.example.banking.Customer;

import com.example.banking.Account.Account;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
       return this.customerService.getAllCustomers();
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer1) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //Customer customer1 = objectMapper.readValue(customer, Customer.class);
        System.out.println("-------------" +customer1.toString());
        //Customer customer1 = new Customer("xxx", "yyyy","zzzz");
        System.out.println(customer1.toString());
        Account account = new Account();
        Customer createdCustomer = this.customerService.addCustomer(customer1);
        return ResponseEntity.status(201).body(createdCustomer);
    }
}
