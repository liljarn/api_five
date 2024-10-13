package com.example.api5.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "themes")
@Setter
@Getter
@NoArgsConstructor
public class ThemeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "theme", unique = true, nullable = false)
    private String theme;

    @OneToMany(mappedBy = "theme", cascade = CascadeType.ALL)
    private List<QuoteEntity> quotes = new ArrayList<>();

    public void addQuote(QuoteEntity quote) {
        quotes.add(quote);
        quote.setTheme(this);
    }
}
