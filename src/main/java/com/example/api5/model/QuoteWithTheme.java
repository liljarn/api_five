package com.example.api5.model;

import java.util.List;

public record QuoteWithTheme(String theme, List<Quote> quotes) {
}
