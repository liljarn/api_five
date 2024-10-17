package com.example.api5.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "QuoteWithTheme", description = "Вощвращаемая тема со всеми связанными цитатами")
public record QuoteWithTheme(
        @Schema(description = "Тема цитат", example = "Юмор")
        String theme,
        @Schema(description = "Список цитат", example = "[{\"key\":\"a\",\"quote\":\"b\"}]")
        List<Quote> quotes
) {
}
