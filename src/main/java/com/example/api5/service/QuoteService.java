package com.example.api5.service;

import com.example.api5.model.AddQuoteRequest;
import com.example.api5.model.Quote;
import com.example.api5.model.QuoteWithTheme;
import com.example.api5.model.ThemeKeys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final Map<String, Map<String, String>> quoteRepository = new HashMap<>(Map.of(
            "Юмор", new HashMap<>(
                    Map.of(
                            "Бананы", "— Шеф, ну что за бред? Нафига вообще отклеивать эти этикетки от бананов? — Для баланса Вселенной. Ведь где-то там, в Африке, такой же огузок, как ты, приклеивает эти этикетки."
                    )
            ),
            "Философия", new HashMap<>(
                    Map.of(
                            "Ошибки", "Мы не можем не совершать ошибки. Они — часть нашей жизни. А самое главное — они помогают нам найти правильный путь.",
                            "Борьба", "Никогда не сдавайся. Пока ты продолжаешь бороться, судьба всегда может наградить тебя.",
                            "Решения", "Порой нам нужно принять решение, от которого будет зависеть наша судьба... И не только наша. Но иногда мы понимаем: чтобы сделать человека счастливым, правильнее будет его отпустить, как бы тяжело это ни было... Да, так будет лучше... Наверное",
                            "Одиночество", "Солнце светит всем одинаково: будь то нерадивый отец или взбалмошная дочь... Оно равно греет как влюбленным парам, так и одинокому парню, который уже забыл, что такое солнце"
                    )
            )
    ));

    public List<QuoteWithTheme> getAllQuotes() {
        return quoteRepository.entrySet().stream()
                .map(theme ->
                        new QuoteWithTheme(
                                theme.getKey(),
                                theme.getValue().entrySet().stream()
                                        .map(quote ->
                                                new Quote(
                                                        quote.getKey(),
                                                        quote.getValue()
                                                )
                                        ).toList()
                        )
                ).toList();
    }

    public Quote getQuote(String key, String theme) {
        return new Quote(key, quoteRepository.get(theme).get(key));
    }

    public List<ThemeKeys> getQuoteKeys() {
        return quoteRepository.entrySet().stream()
                .map(theme ->
                        new ThemeKeys(
                                theme.getKey(),
                                theme.getValue().keySet().stream().toList()
                        )
                ).toList();

    }

    public List<String> getQuoteThemes() {
        return quoteRepository.keySet().stream().toList();
    }

    public List<Quote> getQuotesByTheme(String theme) {
        Map<String, String> quotes = quoteRepository.get(theme);
        return quotes.entrySet().stream()
                .map(quoteKey ->
                        new Quote(
                                quoteKey.getKey(),
                                quoteKey.getValue()
                        )
                )
                .toList();
    }

    public void addQuote(AddQuoteRequest request) {
        if (quoteRepository.containsKey(request.theme())) {
            quoteRepository.get(request.theme()).put(request.key(), request.quote());
        } else {
            quoteRepository.put(request.theme(), new HashMap<>(Map.of(request.key(), request.quote())));
        }
    }

    public void deleteQuote(String key, String theme) {
        quoteRepository.get(theme).remove(key);

        if (quoteRepository.get(theme).isEmpty()) {
            quoteRepository.remove(theme);
        }
    }
}
