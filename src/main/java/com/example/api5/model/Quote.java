package com.example.api5.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Quote", description = "Возвращаемая цитата")
public record Quote(
        @Schema(description = "Ключ цитаты", example = "Бананы")
        String quoteKey,
        @Schema(description = "Цитата", example = "— Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов? — Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки.")
        String quote
) {
}
