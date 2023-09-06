package com.service;

import com.DTO.QuoteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
@PropertySource("classpath:api.properties")
public class QuoteImpl implements Quote{

    @Autowired
    RestTemplate restTemplate;
    @Value("${quote.url}")
    String url;
    @Override
    public QuoteDTO getQuote() {
        ResponseEntity<QuoteDTO[]> movieQuoteResponseEntity= restTemplate.getForEntity(url, QuoteDTO[].class);
        return Objects.requireNonNull(movieQuoteResponseEntity.getBody())[0];
    }
}
