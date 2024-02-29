package com.example.Banking.application.BankingService;

import com.example.Banking.application.BankingRepository.BankingRepository;
import com.example.Banking.application.Model.Banking;
import com.example.Banking.application.Model.DTO.WithdrawRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WithdrawService {
    @Autowired
    BankingRepository bankingRepository;

    public WithdrawService(BankingRepository bankingRepository) {
        this.bankingRepository = bankingRepository;
    }


    @Transactional
    public boolean withdrawMoney(WithdrawRequest request) {
        Optional<Banking> optionalAccount = bankingRepository.findById(request.getAccountId());
        if (optionalAccount.isPresent()) {
            Banking account = optionalAccount.get();
            if (account.getBalance() - request.getAmount() >= 1000) {
                double newBalance = account.getBalance() - request.getAmount();
                account.setBalance(newBalance);
                bankingRepository.save(account);
                return true;
            }
        }
        return false;
    }

}
