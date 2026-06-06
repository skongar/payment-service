package com.lloyds.payments.dataloader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lloyds.payments.dao.entity.AccountEntity;
import com.lloyds.payments.dao.repo.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AccountDataLoader implements CommandLineRunner {

    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {

        List<AccountEntity> accounts =
                objectMapper.readValue(
                        new ClassPathResource("accounts.json").getInputStream(),
                        new TypeReference<>() {
                        });

        accountRepository.saveAll(accounts);

        System.out.println("Loaded accounts: " + accounts.size());
    }
}
