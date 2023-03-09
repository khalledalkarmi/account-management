package com.progressoft.application.resources;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountRequest {
    private String customerId;
    private String balance;
}
