package com.example.banking.Transaction;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @GetMapping
    ResponseEntity getLastTenTransaction(@RequestParam("accountNumber") String accountNumber) {
        List<Transaction> lastTransactions = transactionService.listRecentTransactions(accountNumber);
        return ResponseEntity.status(201).body(lastTransactions);
    }

    @GetMapping(path="/list")
    ResponseEntity getAllTransactions() {
        List<Transaction> allTransactions = transactionService.getAllTransactions();
        return ResponseEntity.ok().body(allTransactions);
    }

    @GetMapping(path="/query")
    ResponseEntity findTransactionByTransactionCode(@RequestParam("transactionCode") String transactionCode) {
        Transaction transaction = transactionService.queryTransaction(transactionCode);
        return ResponseEntity.status(201).body(transaction);
    }
}
