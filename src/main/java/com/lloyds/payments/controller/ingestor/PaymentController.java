package com.lloyds.payments.controller.ingestor;

import com.lloyds.payments.model.PaymentAcceptedResponse;
import com.lloyds.payments.model.PaymentRequest;
import com.lloyds.payments.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentAcceptedResponse> createPayment(
            @Valid @RequestBody PaymentRequest request) {

        return ResponseEntity.accepted()
                .body(new PaymentAcceptedResponse(
                        paymentService.submit(request)));
    }
}
