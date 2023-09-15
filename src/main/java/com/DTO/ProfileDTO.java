package com.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class ProfileDTO {
    String name;
    LocalDate createdAt;
    String poster;
    boolean isMyProfile;
}
