package com.nklpm.template.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentConsumer {

    private final PaymentService paymentService;
    private ObjectMapper objectMapper;

    public PaymentConsumer(final PaymentService paymentService) {
        this.paymentService = paymentService;
        this.objectMapper = new ObjectMapper();
    }

    public void consumeEvent(String eventName, String message) throws JsonProcessingException {
        switch (eventName) {
            case "CREATE": createPayment(message);
                break;
            case "APPROVE": approvePayment(message);
                break;
            case "CANCEL": cancelPayment(message);
                break;
            default: throw new IllegalArgumentException(String.format(
                "Event name %s is a not valid option",
                eventName
            ));
        }
    }

    private void createPayment(String message) throws JsonProcessingException {
        PaymentEventDto paymentEventDto = objectMapper.readValue(message, PaymentEventDto.class);
        paymentService.createPayment(
            paymentEventDto.getOriginAccount(),
            paymentEventDto.getDestinationAccount(),
            paymentEventDto.getAmount(),
            paymentEventDto.getPurpose()
        );
        System.out.println("Payment created");
    }

    private void cancelPayment(String message) throws JsonProcessingException {
        PaymentEventDto paymentEventDto = objectMapper.readValue(message, PaymentEventDto.class);
        paymentService.cancelPayment(paymentEventDto.getPaymentId());
        System.out.println("Payment cancelled");
    }

    private void approvePayment(String message) throws JsonProcessingException {
        PaymentEventDto paymentEventDto = objectMapper.readValue(message, PaymentEventDto.class);
        paymentService.approvePayment(paymentEventDto.getPaymentId());
        System.out.println("Payment approved");
    }
}
