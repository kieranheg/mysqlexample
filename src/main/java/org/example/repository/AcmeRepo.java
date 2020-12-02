package org.example.repository;

import org.example.model.AcmeRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcmeRepo extends JpaRepository<AcmeRequestEntity, Long> {
}
