package com.example.Banking.application.Model.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.antlr.v4.runtime.misc.NotNull;

public class WithdrawRequest {

    private int accountId;

    private double amount;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
