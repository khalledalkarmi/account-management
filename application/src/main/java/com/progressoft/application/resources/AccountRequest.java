package com.progressoft.application.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
public class AccountRequest {
    private String customerId;
    private String balance;
}
