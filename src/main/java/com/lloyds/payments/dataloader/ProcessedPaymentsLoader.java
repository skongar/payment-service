package com.lloyds.payments.dataloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloyds.payments.dao.entity.ProcessedPaymentEntity;
import com.lloyds.payments.dao.repo.ProcessedPaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProcessedPaymentsLoader
        implements CommandLineRunner {

    private final ProcessedPaymentRepository repository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        List<ProcessedPaymentEntity> payments =
                objectMapper.readValue(
                        new ClassPathResource(
                                "processed-payments.json")
                                .getInputStream(),
                        new TypeReference<>() {
                        });

        repository.saveAll(payments);

        System.out.println(
                "Loaded Processed Payments: "
                        + payments.size());
    }
}
