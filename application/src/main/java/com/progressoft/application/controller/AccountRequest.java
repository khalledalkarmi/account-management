package com.progressoft.application.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
public class AccountRequest {
    private String customerId;
    private BigDecimal balance;
}
