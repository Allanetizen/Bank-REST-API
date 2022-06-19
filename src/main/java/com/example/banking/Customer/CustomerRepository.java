package com.example.banking.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    @Query("SELECT customer from Customer customer WHERE customer.CustomerId =:customerId")
    Customer findOneByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT customer from Customer customer WHERE customer.CustomerId =:customerId")
    Optional<Customer> findByCustomerId(@Param("customerId") String customerId);

}
