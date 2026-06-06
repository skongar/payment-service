package com.lloyds.payments.listener;

import com.lloyds.payments.model.PaymentEvent;
import com.lloyds.payments.service.PaymentProcessorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PaymentsListener {

    private final PaymentProcessorService paymentProcessorService;

    public PaymentsListener(PaymentProcessorService paymentProcessorService) {
        this.paymentProcessorService = paymentProcessorService;
    }

    @KafkaListener(
            topics = "payments.submitted",
            groupId = "payment-processor-group")
    public void consume(PaymentEvent event, Acknowledgment acknowledgment) throws Exception{

        try {
            paymentProcessorService.process(event);
            acknowledgment.acknowledge();
        } catch (Exception ex) {
            log.info("Exception occurred while consuming message .. retrying");
            throw new Exception(ex);
        }
    }
}
