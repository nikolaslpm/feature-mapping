package com.nklpm.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private BankService bankService;

    private Payment payment;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        this.payment = new Payment();
        this.paymentService = new PaymentService(bankService, payment);
    }

    /**
     * Given payment creation is requested
     * When Bank server responds success
     * Then payment state should be WAITING
     */
    @Test
    void shouldCreatePayment() {
        given(bankService.executePayment(any(Payment.class)))
            .willReturn(UUID.randomUUID().toString());

        Payment actualPayment = paymentService
            .createPayment("123", "321", BigDecimal.TEN, "something");

        assertThat(actualPayment, is(notNullValue()));
        assertThat(actualPayment.getState(), is(Payment.State.WAITING));
        assertThat(actualPayment.getOriginAccount(), is("123"));
    }

    /**
     * Given payment cancellation is requested
     * When Bank server responds success
     * Then payment state should be CANCEL
     */
    @Test
    void shouldCancelPayment() {
        payment.setId("123");
        Payment actualPayment = paymentService.cancelPayment("123");

        assertThat(actualPayment, is(notNullValue()));
        assertThat(actualPayment.getState(), is(Payment.State.CANCELED));
        verify(bankService).cancelPaymentById(eq("123"));
    }

    /**
     * When payment approval is requested
     * Then payment state should be APPROVED
     */
    @Test
    void shouldApprovePayment() {
        payment.setId("123");
        Payment actualPayment = paymentService.approvePayment("123");

        assertThat(actualPayment, is(notNullValue()));
        assertThat(actualPayment.getState(), is(Payment.State.APPROVED));
    }
}