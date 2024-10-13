package com.example.api5.entity;

import com.example.api5.model.Quote;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quotes")
@Getter
@Setter
@NoArgsConstructor
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "quote_key", nullable = false, unique = true)
    private String quoteKey;

    @Column(name = "quote", nullable = false)
    private String quote;

    @ManyToOne
    @JoinColumn(name = "theme_id", nullable = false)
    private ThemeEntity theme;

    public Quote toQuote() {
        return new Quote(quoteKey, quote);
    }
}
