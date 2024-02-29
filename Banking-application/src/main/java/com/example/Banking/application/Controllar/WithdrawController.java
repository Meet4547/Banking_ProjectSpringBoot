package com.example.Banking.application.Controllar;

import com.example.Banking.application.BankingService.WithdrawService;
import com.example.Banking.application.Model.DTO.WithdrawRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WithdrawController {
    WithdrawService withdrawService;
    public WithdrawController(WithdrawService withdrawService) {
        this.withdrawService = withdrawService;
    }

    @PostMapping("/account/withdraw")
    public ResponseEntity<String> withdrawMoney(@RequestBody @Validated WithdrawRequest request) {
        boolean success = withdrawService.withdrawMoney(request);
        if (success) {
            return ResponseEntity.ok("Withdrawal successful");
        } else {
            return ResponseEntity.badRequest().body("Withdrawal failed. Insufficient balance or invalid account ID.");
        }
    }
}
