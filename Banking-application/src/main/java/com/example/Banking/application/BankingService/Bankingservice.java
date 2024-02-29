package com.example.Banking.application.BankingService;

import com.example.Banking.application.BankingRepository.BankingRepository;
import com.example.Banking.application.Model.Banking;
import com.example.Banking.application.Model.DTO.DepositRequest;
import com.example.Banking.application.Model.DTO.TransferRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Bankingservice {
    @Autowired
    BankingRepository bankingRepository;

    public Banking createAccount(Banking account) {
        return bankingRepository.save(account);
    }

    public List<Banking> getAllAccounts() {
        return bankingRepository.findAll();
    }

    public void depositMoney(DepositRequest request) {
        Banking account = bankingRepository.findById(request.getAccountId()).get();
                //.orElseThrow(() -> new EntityNotFoundException("Account not found"));

        double newBalance = account.getBalance() + request.getAmount();
        account.setBalance(newBalance);
        bankingRepository.save(account);

    }

    public void transferMoney(TransferRequest transferRequest) {
        // Retrieve accounts from the database
        Optional<Banking> fromAccountOptional = bankingRepository.findById(transferRequest.getFromAccountId());
        Optional<Banking> toAccountOptional = bankingRepository.findById(transferRequest.getToAccountId());

        if (fromAccountOptional.isPresent() && toAccountOptional.isPresent()) {
            Banking fromAccount = fromAccountOptional.get();
            Banking toAccount = toAccountOptional.get();

            // Check if the from account has sufficient balance
            if (fromAccount.getBalance() >= transferRequest.getAmount()) {
                // Deduct the amount from the sender's account
                fromAccount.setBalance(fromAccount.getBalance() - transferRequest.getAmount());

                // Add the amount to the recipient's account
                toAccount.setBalance(toAccount.getBalance() + transferRequest.getAmount());

                // Update both accounts in the database
                bankingRepository.save(fromAccount);
                bankingRepository.save(toAccount);
            } else {
                throw new IllegalArgumentException("Insufficient balance in the sender's account");
            }
        } else {
            throw new IllegalArgumentException("One or both accounts not found");
        }
    }

//    public double getAccountBalance(int accountId) {
//        Banking bankingAccount = bankingRepository.findById(accountId)
//        orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));
//        return bankingAccount.getBalance();
//    }

    public double getAccountBalance(int accountId) {
        // Fetch account balance from the database using the repository
        Banking bankingAccount = bankingRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account with ID " + accountId + " not found"));

        return bankingAccount.getBalance();
    }

//    public Banking createAccount(Banking account) {
//        return bankingRepository.save(account);
//    }
}