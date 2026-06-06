package com.lloyds.payments.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class AccountEntity {

    @Id
    private String accountId;

    private String accountName;

    private String accountType;

    private String status;

    private String currency;

    private LocalDate openedDate;
}
