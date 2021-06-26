package com.nklpm.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
public class Payment {

    public enum State {
        APPROVED,
        WAITING,
        CANCELED
    }

    private String id;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private String purpose;
    private State state;
}
