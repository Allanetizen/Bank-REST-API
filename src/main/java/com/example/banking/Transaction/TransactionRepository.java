package com.example.banking.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findTop10ByOrderByCreatedAtDesc();
    // ORDER BY transaction.timestamp ASC LIMIT 10
    @Query("SELECT transaction from Transaction transaction  WHERE transaction.accountNumber =:accountNumber ORDER BY transaction.createdAt DESC")
    List<Transaction> findLastTenTransactions(@Param("accountNumber")String accountNumber);

    @Query("SELECT transaction from Transaction transaction WHERE transaction.transactionCode =:transactionCode")
    Optional<Transaction> searchTransaction(@Param("transactionCode") String transactionCode);
}
