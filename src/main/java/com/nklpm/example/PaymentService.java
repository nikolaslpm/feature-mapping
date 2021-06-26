package com.nklpm.example;

import java.math.BigDecimal;

public class PaymentService {

    private BankService bankService;
    private Payment payment;

    public PaymentService(BankService bankService, Payment payment) {
        this.bankService = bankService;
        this.payment = payment;
    }

    public Payment createPayment(String originAccount, String destinationAccount, BigDecimal amount, String purpose) {
        Payment requestPayment = Payment.builder()
            .originAccount(originAccount)
            .destinationAccount(destinationAccount)
            .amount(amount)
            .purpose(purpose)
            .build();

        String paymentId = bankService.executePayment(requestPayment);

        requestPayment.setId(paymentId);
        requestPayment.setState(Payment.State.WAITING);
        return savePayment(requestPayment);
    }

    public Payment cancelPayment(String paymentId) {
        Payment payment = findPaymentById(paymentId);

        bankService.cancelPaymentById(paymentId);
        payment.setState(Payment.State.CANCELED);
        return savePayment(payment);
    }

    public Payment findPaymentById(String paymentId) {
        if (payment != null && payment.getId().equalsIgnoreCase(paymentId)) {
            return payment;
        }
        throw new IllegalArgumentException("Payment dont exist with paymentId: " + paymentId);
    }

    public Payment savePayment(Payment payment) {
        return this.payment = payment;
    }

    public Payment approvePayment(String paymentId) {
        Payment payment = findPaymentById(paymentId);
        payment.setState(Payment.State.APPROVED);
        return savePayment(payment);
    }
}
