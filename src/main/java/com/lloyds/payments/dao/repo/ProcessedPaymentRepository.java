package com.lloyds.payments.dao.repo;

import com.lloyds.payments.dao.entity.ProcessedPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProcessedPaymentRepository
        extends JpaRepository<ProcessedPaymentEntity, UUID> {
}
