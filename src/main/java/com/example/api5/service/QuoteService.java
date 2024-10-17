package com.example.api5.service;

import com.example.api5.entity.QuoteEntity;
import com.example.api5.entity.ThemeEntity;
import com.example.api5.model.AddQuoteRequest;
import com.example.api5.model.CachedQuote;
import com.example.api5.model.Quote;
import com.example.api5.model.QuoteWithTheme;
import com.example.api5.repository.QuoteRepository;
import com.example.api5.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final ThemeRepository themeRepository;
    private final Map<String, CachedQuote> quoteCache = new HashMap<>();

    @Transactional(readOnly = true)
    public List<QuoteWithTheme> getAllQuotes() {
        return themeRepository.findAll()
                .stream()
                .map(themeEntity -> new QuoteWithTheme(
                        themeEntity.getTheme(),
                        themeEntity.getQuotes()
                                .stream()
                                .map(QuoteEntity::toQuote)
                                .toList())
                )
                .toList();
    }

    @Transactional(readOnly = true)
    public Quote getQuote(String key) {
        CachedQuote cachedQuote = quoteCache.get(key);

        if (cachedQuote == null) {
            Quote quote = quoteRepository.findByQuoteKey(key).orElseThrow(RuntimeException::new).toQuote();
            quoteCache.put(quote.quoteKey(), new CachedQuote(quote, LocalDateTime.now()));
            return quote;
        } else {
            quoteCache.put(cachedQuote.quote().quoteKey(), new CachedQuote(cachedQuote.quote(), LocalDateTime.now()));
            return cachedQuote.quote();
        }
    }

    @Transactional(readOnly = true)
    public List<String> getQuoteKeys() {
        return quoteRepository.findAll().stream().map(QuoteEntity::getQuoteKey).toList();
    }

    @Transactional(readOnly = true)
    public List<String> getQuoteThemes() {
        return themeRepository.findAll().stream().map(ThemeEntity::getTheme).toList();
    }

    @Transactional(readOnly = true)
    public List<Quote> getQuotesByTheme(String theme) {
        return themeRepository.findByTheme(theme).orElseThrow(RuntimeException::new).getQuotes()
                .stream()
                .map(QuoteEntity::toQuote)
                .toList();
    }

    @Transactional
    public Quote addQuote(AddQuoteRequest request) {
        ThemeEntity themeEntity = themeRepository.findByTheme(request.theme())
                .orElseGet(() -> {
                    ThemeEntity newTheme = new ThemeEntity();
                    newTheme.setTheme(request.theme());
                    return newTheme;
                });

        Optional<QuoteEntity> optionalQuote = quoteRepository.findByQuoteKey(request.key());
        if (optionalQuote.isPresent()) {
            QuoteEntity existingQuote = optionalQuote.get();
            if (existingQuote.getQuote().equals(request.quote())) {
                return existingQuote.toQuote();
            } else {
                throw new RuntimeException("Цитата с данным ключом уже существует, но текст отличается.");
            }
        }

        QuoteEntity newQuote = new QuoteEntity();
        newQuote.setQuoteKey(request.key());
        newQuote.setQuote(request.quote());
        newQuote.setTheme(themeEntity);

        themeEntity.addQuote(newQuote);

        themeRepository.save(themeEntity);

        return newQuote.toQuote();
    }

    @Transactional
    public Quote deleteQuote(String key) {
        quoteCache.remove(key);
        QuoteEntity entity = quoteRepository.findByQuoteKey(key).orElseThrow(() -> new RuntimeException("Цитата не найдена"));
        ThemeEntity theme = entity.getTheme();

        quoteRepository.delete(entity);

        theme.getQuotes().remove(entity);

        if (theme.getQuotes().isEmpty()) {
            themeRepository.delete(theme);
        }

        return entity.toQuote();
    }

    public void clearCache() {
        for (String key : quoteCache.keySet()) {
            CachedQuote quote = quoteCache.get(key);
            if (quote.usageTime().plusMinutes(5).isBefore(LocalDateTime.now())) {
                quoteCache.remove(key);
            }
        }
    }
}
