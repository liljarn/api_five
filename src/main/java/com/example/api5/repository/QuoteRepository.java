package com.example.api5.repository;

import com.example.api5.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    Optional<QuoteEntity> findByQuoteKey(String key);
}
