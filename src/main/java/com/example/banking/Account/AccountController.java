package com.example.banking.Account;

import com.example.banking.models.DepositRequest;
import com.example.banking.models.FundTransferRequest;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/balance")
    public ResponseEntity<?> getBalance(@RequestParam("customerId") String customerId) throws Exception {
        float balance = accountService.getBalance(customerId);
        return ResponseEntity.status(200).body(balance);
    }

    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        List<Account> accountList = accountService.getAllAccounts();
        return ResponseEntity.status(200).body(accountList);
    }

    @PostMapping(path="/deposit")
    public ResponseEntity depositToAccount(@RequestBody DepositRequest depositRequest) throws Exception {
        float updatedBalance = this.accountService.creditAccount(depositRequest.getAccountNumber(), depositRequest.getAmount());
        try {
            return ResponseEntity.status(201).body(updatedBalance);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.toString());
        }

    }

    @PostMapping(path="/fundTransfer")
    public ResponseEntity fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) throws Exception {
        try {
            float newBalance = this.accountService.fundTransfer(fundTransferRequest.getDebitAccount(),
                    fundTransferRequest.getCreditAccount(), fundTransferRequest.getAmount());
            return ResponseEntity.status(201).body(newBalance);
        } catch(Exception e) {
            return ResponseEntity.status(400).body(e.toString());
        }
    }
}
