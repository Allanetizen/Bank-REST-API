package com.example.banking.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void addTransaction(String transactionCode, String accountNumber, String action, float amount) {
        Transaction transaction = new Transaction(transactionCode, accountNumber,action, amount);
        transactionRepository.save(transaction);
    }
    public List<Transaction> listRecentTransactions(String accountNumber) {
        List<Transaction> lastTenTransactions =  transactionRepository.findLastTenTransactions(accountNumber);
        if(lastTenTransactions.size() > 10)
            lastTenTransactions = lastTenTransactions.subList(0,9);
        else
            return lastTenTransactions;
        return lastTenTransactions;
        //return transactionRepository.findTop10ByOrderByCreatedAtDesc();
    }

    public List<Transaction> getAllTransactions() {
        return this.transactionRepository.findAll();
    }
    public Transaction queryTransaction(String transactionCode) {
        Transaction transaction = (this.transactionRepository.searchTransaction(transactionCode)).get();
        return transaction;
    }
}
