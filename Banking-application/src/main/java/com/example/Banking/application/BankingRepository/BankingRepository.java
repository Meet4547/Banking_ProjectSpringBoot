package com.example.Banking.application.BankingRepository;

import com.example.Banking.application.Model.Banking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankingRepository extends JpaRepository<Banking,Integer> {
}