package com.example.api5.controller;

import com.example.api5.model.AddQuoteRequest;
import com.example.api5.model.Quote;
import com.example.api5.model.QuoteWithTheme;
import com.example.api5.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class QuoteController {
    private final QuoteService quoteService;

    @GetMapping("/quote")
    public Quote getQuoteByKey(@RequestParam String key) {
        return quoteService.getQuote(key);
    }

    @GetMapping("/quotes")
    public List<QuoteWithTheme> getQuotes() {
        return quoteService.getAllQuotes();
    }

    @GetMapping("/keys")
    public List<String> getQuoteKeys() {
        return quoteService.getQuoteKeys();
    }

    @GetMapping("/themes")
    public List<String> getQuoteThemes() {
        return quoteService.getQuoteThemes();
    }

    @GetMapping("/quotes/theme")
    public List<Quote> getQuotesByTheme(@RequestParam String theme) {
        return quoteService.getQuotesByTheme(theme);
    }

    @PostMapping("/quote")
    public Quote addQuote(@RequestBody AddQuoteRequest request) {
        return quoteService.addQuote(request);
    }

    @DeleteMapping("/quote")
    public Quote deleteQuote(@RequestParam String key) {
        return quoteService.deleteQuote(key);
    }
}
