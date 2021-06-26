package com.nklpm.example;

import java.util.UUID;

/**
 * Simulates a third party service
 */
public class BankService {

    private String paymentId;

    public String executePayment(Payment requestPayment) {
        if(requestPayment.getAmount().intValue() <= 0) {
            throw new IllegalArgumentException("Amount should be positive!");
        }
        return paymentId = UUID.randomUUID().toString();
    }

    public void cancelPaymentById(String paymentId) {
        if(this.paymentId == null || !this.paymentId.equalsIgnoreCase(paymentId)){
            throw new IllegalArgumentException("PaymentId not found with value: " + paymentId);
        }
    }
}
