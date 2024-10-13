package com.example.api5.jobs;

import com.example.api5.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteCacheJob {
    private final QuoteService quoteService;

    @Scheduled(cron = "* * * * *")
    public void clearQuoteCache() {
        quoteService.clearCache();
    }
}
