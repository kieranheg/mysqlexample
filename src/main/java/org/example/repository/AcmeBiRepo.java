package org.example.repository;

import org.example.model.AcmeBiRequestEntity;
import org.example.model.AcmeUniRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcmeBiRepo extends JpaRepository<AcmeBiRequestEntity, Long> {
}
