package com.example.banking.services;

import com.example.banking.Customer.Customer;
import com.example.banking.Customer.CustomerRepository;
import com.example.banking.User.User;
import com.example.banking.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("-------->"+username);
        /*User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found" +
                "with username " + username));*/
        Optional<Customer> customer1 = customerRepository.findByCustomerId(username);
        System.out.println("---->"+customer1.toString());
        Customer customer = customerRepository.findByCustomerId(username).orElseThrow(() -> new UsernameNotFoundException("User not found" +
                "with username " + username));
        System.out.println(customer.toString());
        return UserDetailsImpl.build(customer);
    }
}
