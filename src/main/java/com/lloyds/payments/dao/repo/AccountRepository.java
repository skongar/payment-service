package com.lloyds.payments.dao.repo;

import com.lloyds.payments.dao.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository
        extends JpaRepository<AccountEntity, String> {
}
