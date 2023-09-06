package com.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
public class WatchedDTO {
    private String imdbID;
    private String title;
    private String year;
    private String poster;
    LocalDate createdAt;
}