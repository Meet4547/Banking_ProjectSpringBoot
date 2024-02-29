package com.example.Banking.application.Controllar;


import com.example.Banking.application.BankingService.Bankingservice;
import com.example.Banking.application.Model.Banking;
import com.example.Banking.application.Model.DTO.DepositRequest;
import com.example.Banking.application.Model.DTO.TransferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class Bankingcontrollar {

    @Autowired
    Bankingservice bankingservice;

    @PostMapping("Open_account")
    public ResponseEntity<Banking> createAccount(@RequestBody Banking account) {
        Banking createdAccount = bankingservice.createAccount(account); // Corrected service method call

        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @GetMapping("account_list")
    public ResponseEntity<List<Banking>> getAllAccounts() {
        List<Banking> accounts = bankingservice.getAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @PostMapping("/account/deposit")
    public ResponseEntity<String> depositMoney(@RequestBody  DepositRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body("Invalid deposit request");
        }

        bankingservice.depositMoney(request);
        return ResponseEntity.ok("Money deposited successfully");
    }
    @PostMapping("/account/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest) {
        // Validate transfer request
        if (transferRequest.getAmount() <= 0) {
            return ResponseEntity.badRequest().body("Amount should be greater than zero");
        }

        // Perform money transfer
        bankingservice.transferMoney(transferRequest);

        return ResponseEntity.ok("Money transferred successfully");
    }
    @GetMapping("/account/balance")
    public ResponseEntity<Double> getAccountBalance(@RequestParam int accountId) {
        double balance = bankingservice.getAccountBalance(accountId);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

}
