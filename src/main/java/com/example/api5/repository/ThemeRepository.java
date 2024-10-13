package com.example.api5.repository;

import com.example.api5.entity.ThemeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThemeRepository extends JpaRepository<ThemeEntity, Long> {
    Optional<ThemeEntity> findByTheme(String theme);
}
