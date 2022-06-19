package com.example.banking.Account;

import com.example.banking.Transaction.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    int min = 100000, max = 1000000;
    private AccountRepository accountRepository;
    private TransactionService transactionService;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public Account createAccount(String customerId) {
        int account_number = (int)Math.floor(Math.random()*(max-min+1)+min);
        Account account = new Account(customerId, ""+account_number,0);

        int receipt_number = (int)Math.floor(Math.random()*(max-min+1)+min);
        String transationCode = "MJW-".concat(""+receipt_number);
        transactionService.addTransaction(transationCode, ""+account_number,"CREDIT",0);
        return this.accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return this.accountRepository.findAll();
    }
    public void debitAccount(String accountNumber, float debitAmount) throws Exception {
        Optional<Account> account = this.accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) {
            throw new Exception("Account Number does not exist");
        } else {
            // check if balance is less than debit amount
            if(account.get().getBalance() < debitAmount) {
                throw new Exception("Insufficient balance");
            } else {
                float balance = account.get().getBalance() - debitAmount;
                account.get().setBalance(balance);
                this.accountRepository.saveAndFlush(account.get());
            }
        }
    }

    public float creditAccount(String accountNumber, float creditAmount) throws Exception {
        Optional<Account> account = this.accountRepository.findByAccountNumber(accountNumber);
        if(account.isEmpty()) {
            throw new Exception("Account Number does not exist");
        } else {
            float balance = account.get().getBalance() + creditAmount;
            account.get().setBalance(balance);
            this.accountRepository.saveAndFlush(account.get());
            int receipt_number = (int)Math.floor(Math.random()*(max-min+1)+min);
            String transationCode = "MJW-".concat(""+receipt_number);
            //int account_number = (int)Math.floor(Math.random()*(max-min+1)+min);
            transactionService.addTransaction(transationCode, accountNumber,"CREDIT",creditAmount);
            Account updatedAccount =  this.accountRepository.findByAccountNumber(accountNumber).get();
            return updatedAccount.getBalance();
        }
    }

    public float fundTransfer(String debitAccount, String creditAccount, float amount) throws Exception {
        Optional<Account> debitaccount = (this.accountRepository.findByAccountNumber(debitAccount));
        Optional<Account> creditaccount = this.accountRepository.findByAccountNumber(creditAccount);
        if(debitaccount.get().getBalance() < amount) {
            throw new Exception("Insufficient balance");
        }
        if(debitaccount.get() == null) {
            throw new Exception("Debit account does not exist");
        }
        if(creditaccount.get() == null) {
            throw new Exception("Credit account does not exist");
        }

        float new_balance_for_debit_account = debitaccount.get().getBalance() - amount;
        float new_balance_for_credit_account = creditaccount.get().getBalance() + amount;

        debitaccount.get().setBalance(new_balance_for_debit_account);
        this.accountRepository.saveAndFlush(debitaccount.get());
        creditaccount.get().setBalance(new_balance_for_credit_account);
        this.accountRepository.saveAndFlush(creditaccount.get());

        int receipt_number = (int)Math.floor(Math.random()*(max-min+1)+min);
        String transationCode = "MJW-".concat(""+receipt_number);
        //int account_number = (int)Math.floor(Math.random()*(max-min+1)+min);
        transactionService.addTransaction(transationCode, debitAccount,"DEBIT",amount);
        transactionService.addTransaction(transationCode, creditAccount,"CREDIT",amount);
        return new_balance_for_debit_account;
    }

    public float getBalance(String customerId) throws Exception {
        Optional<Account> account = this.accountRepository.findByCustomerId(customerId);
        if(account.isEmpty()) {
            throw new Exception("Account Number does not exist");
        } else {
            return account.get().getBalance();
        }
    }


}
