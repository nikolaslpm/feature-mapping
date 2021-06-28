package com.nklpm.template.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentConsumerIT {

    private PaymentConsumer paymentConsumer;
    private PaymentService paymentService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.paymentService = new PaymentService(new BankService(), new Payment());
        this.paymentConsumer = new PaymentConsumer(paymentService);
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Given event is CREATE
     * When request payment creation
     * Then should process successfully
     */
    @Test
    void shouldCreatePayment() throws JsonProcessingException {
        PaymentEventDto paymentEventDto = PaymentEventDto.builder()
            .originAccount("0001")
            .destinationAccount("12345")
            .purpose("mobile bill order 456")
            .amount(BigDecimal.TEN)
            .build();

        String message = objectMapper.writeValueAsString(paymentEventDto);
        paymentConsumer.consumeEvent("CREATE", message);
    }

    /**
     * Given Payment id dont exist
     * When request payment cancellation
     * Then should throw exception
     */
    @Test
    void givenPaymentNotFoundByIdWhenCancelPaymentThenShouldThrowException() throws JsonProcessingException {
        PaymentEventDto paymentEventDto = PaymentEventDto.builder()
            .paymentId("123")
            .build();
        paymentService.savePayment(Payment.builder().id("321").build());

        String message = objectMapper.writeValueAsString(paymentEventDto);
        assertThrows(IllegalArgumentException.class, () -> paymentConsumer.consumeEvent("CANCEL", message));
    }
}