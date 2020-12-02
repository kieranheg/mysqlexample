package org.example.repository;

import org.example.model.AcmeUniRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcmeUniRepo extends JpaRepository<AcmeUniRequestEntity, Long> {
}
