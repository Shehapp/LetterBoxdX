package com.service;

import com.DTO.QuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;
import java.util.concurrent.*;

@Service
@PropertySource("classpath:api.properties")
public class QuoteImpl implements Quote{

    @Autowired
    RestTemplate restTemplate;
    @Value("${quote.url}")
    String url;
    @Override
    public QuoteDTO getQuote() {


        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<QuoteDTO> future = executor.submit(() -> {
                ResponseEntity<QuoteDTO[]> movieQuoteResponseEntity= restTemplate.getForEntity(url, QuoteDTO[].class);
                return Objects.requireNonNull(movieQuoteResponseEntity.getBody())[0];
            });

            try {
                return future.get(1, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } finally {
            executor.shutdown();
        }

        throw new RuntimeException("No quote");
    }

}
