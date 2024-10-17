package com.example.api5.controller;

import com.example.api5.model.AddQuoteRequest;
import com.example.api5.model.Quote;
import com.example.api5.model.QuoteWithTheme;
import com.example.api5.service.QuoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "Цитаты из сериала кухня", description = "Методы для получения, добавления и удаления цитат")
public class QuoteController {
    private final QuoteService quoteService;

    @Operation(operationId = "getQuoteByKey", summary = "Получить цитату по ключу")
    @GetMapping("/quote")
    public Quote getQuoteByKey(
            @Parameter(name = "key", description = "Ключ искомой цитаты", in = ParameterIn.QUERY)
            @RequestParam String key
    ) {
        return quoteService.getQuote(key);
    }

    @Operation(operationId = "getQuotes", summary = "Получить список всех цитат по темам")
    @GetMapping("/quotes")
    public List<QuoteWithTheme> getQuotes() {
        return quoteService.getAllQuotes();
    }

    @Operation(operationId = "getQuoteKeys", summary = "Получить список всех ключей цитат")
    @GetMapping("/keys")
    public List<String> getQuoteKeys() {
        return quoteService.getQuoteKeys();
    }

    @Operation(operationId = "getQuoteThemes", summary = "Получить список всех тем")
    @GetMapping("/themes")
    public List<String> getQuoteThemes() {
        return quoteService.getQuoteThemes();
    }

    @Operation(operationId = "getQuotesByTheme", summary = "Получить список всех цитат по теме")
    @GetMapping("/quotes/theme")
    public List<Quote> getQuotesByTheme(
            @Parameter(name = "theme", description = "Название искомой темы", in = ParameterIn.QUERY)
            @RequestParam String theme
    ) {
        return quoteService.getQuotesByTheme(theme);
    }

    @Operation(operationId = "addQuote", summary = "Добавить цитату")
    @PostMapping("/quote")
    public Quote addQuote(@RequestBody AddQuoteRequest request) {
        return quoteService.addQuote(request);
    }

    @Operation(operationId = "deleteQuote", summary = "Удалить цитату")
    @DeleteMapping("/quote")
    public Quote deleteQuote(@RequestParam String key) {
        return quoteService.deleteQuote(key);
    }
}
