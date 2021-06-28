package com.nklpm.template.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentEventDto {

    private String paymentId;
    private String originAccount;
    private String destinationAccount;
    private BigDecimal amount;
    private String purpose;
}
