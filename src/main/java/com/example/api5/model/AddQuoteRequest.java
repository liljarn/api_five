package com.example.api5.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "AddQuoteRequest", description = "Запрос на добавление цитаты")
public record AddQuoteRequest(
        @Schema(description = "Ключ цитаты", example = "Бананы")
        String key,
        @Schema(description = "Цитата", example = "— Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов? — Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки.")
        String quote,
        @Schema(description = "Тема цитаты", example = "Юмор")
        String theme
) {
}
