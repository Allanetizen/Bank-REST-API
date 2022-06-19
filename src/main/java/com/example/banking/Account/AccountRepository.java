package com.example.banking.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT account FROM Account account WHERE account.accountNumber =:accountNumber")
    Optional<Account> findByAccountNumber(@Param("accountNumber")String accountNumber);

    @Query("SELECT account FROM Account account WHERE account.customerId =:customerId")
    Optional<Account> findByCustomerId(@Param("customerId")String customerId);
}
