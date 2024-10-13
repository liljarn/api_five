package com.example.api5.model;

import java.time.LocalDateTime;

public record CachedQuote(Quote quote, LocalDateTime usageTime) {
}
