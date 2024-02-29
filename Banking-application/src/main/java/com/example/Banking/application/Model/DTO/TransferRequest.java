package com.example.Banking.application.Model.DTO;

public class TransferRequest {
    private int fromAccountId;
    private int toAccountId;
//    private String fromAccountHolderName;
//    private String toAccountHolderName;
    private double amount;

    public int getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

//    public String getFromAccountHolderName() {
//        return fromAccountHolderName;
//    }
//
//    public void setFromAccountHolderName(String fromAccountHolderName) {
//        this.fromAccountHolderName = fromAccountHolderName;
//    }
//
//    public String getToAccountHolderName() {
//        return toAccountHolderName;
//    }
//
//    public void setToAccountHolderName(String toAccountHolderName) {
//        this.toAccountHolderName = toAccountHolderName;
//    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

