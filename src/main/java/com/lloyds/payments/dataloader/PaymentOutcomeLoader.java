package com.lloyds.payments.dataloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloyds.payments.dao.entity.PaymentOutcomeEntity;
import com.lloyds.payments.dao.repo.PaymentOutcomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentOutcomeLoader
        implements CommandLineRunner {

    private final PaymentOutcomeRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        List<PaymentOutcomeEntity> outcomes =
                objectMapper.readValue(
                        new ClassPathResource(
                                "payment-outcomes.json")
                                .getInputStream(),
                        new TypeReference<>() {
                        });

        repository.saveAll(outcomes);

        System.out.println(
                "Loaded Payment Outcomes: "
                        + outcomes.size());
    }
}
