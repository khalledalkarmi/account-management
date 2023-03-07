package model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(access = AccessLevel.PUBLIC)
@Data
public class Account {
    long id;
    private String customerId;
    private long accountNumber;
    private BigDecimal availableBalance;
    private Status status;
    private LocalDateTime creationDate;
}
