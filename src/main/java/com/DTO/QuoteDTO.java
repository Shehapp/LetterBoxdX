package com.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class QuoteDTO {
    String author;
    String quote;

    public QuoteDTO(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }
}
