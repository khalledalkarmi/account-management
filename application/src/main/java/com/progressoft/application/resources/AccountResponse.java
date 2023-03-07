package com.progressoft.application.resources;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Data
public class AccountResponse {
    private long accountNumber;
    private BigDecimal availableBalance;
    private Status status;
    private LocalDateTime creationDate;
}
