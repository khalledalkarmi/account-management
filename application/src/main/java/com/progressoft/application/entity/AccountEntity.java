package com.progressoft.application.entity;

import jakarta.persistence.*;
import lombok.*;
import model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Entity
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String customerId;
    private long accountNumber;
    private BigDecimal availableBalance;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime creationDate = LocalDateTime.now();

}

