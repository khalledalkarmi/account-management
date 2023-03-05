package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Account {
    private String id;
    private long accountNumber;
    private BigDecimal availableBalance;
    private Status status;
    private LocalDateTime creationDate;
}
