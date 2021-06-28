package com.nklpm.template.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PaymentConsumerTest {

    @InjectMocks
    private PaymentConsumer paymentConsumer;
    @Mock
    private PaymentService paymentService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Given event is CREATE
     * When request payment creation
     * Then should process successfully
     */
    @Disabled("ASDADS")
    @Test
    void shouldCreatePayment() throws JsonProcessingException {
        PaymentEventDto paymentEventDto = PaymentEventDto.builder()
            .originAccount("0001")
            .destinationAccount("12345")
            .purpose("mobile bill order 456")
            .amount(BigDecimal.ZERO)
            .build();

        String message = objectMapper.writeValueAsString(paymentEventDto);
        paymentConsumer.consumeEvent("CREATE", message);

        verify(paymentService, times(1))
            .createPayment(
                paymentEventDto.getOriginAccount(),
                paymentEventDto.getDestinationAccount(),
                paymentEventDto.getAmount(),
                paymentEventDto.getPurpose()
            );
    }

    /**
     * Given event is CANCEL
     * And payment already exist
     * When request payment cancellation
     * Then should process successfully
     */
    @Test
    void shouldCancelPayment() throws JsonProcessingException {
        PaymentEventDto paymentEventDto = PaymentEventDto.builder()
            .paymentId("123")
            .build();

        String message = objectMapper.writeValueAsString(paymentEventDto);
        paymentConsumer.consumeEvent("CANCEL" , message);

        verify(paymentService, times(1)).cancelPayment("123");
    }

    /**
     * Given event is APPROVE
     * And payment is waiting approval
     * When request payment approval
     * Then should process successfully
     */
    @Test
    void shouldApprovePayment() throws JsonProcessingException {
        PaymentEventDto paymentEventDto = PaymentEventDto.builder()
            .paymentId("123")
            .build();

        String message = objectMapper.writeValueAsString(paymentEventDto);
        paymentConsumer.consumeEvent("APPROVE", message);

        verify(paymentService, times(1)).approvePayment("123");
    }
}