package com.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
public class UserLogDTO {
    private String userName;
    private String description;
    private LocalDate createdAt;
    private String id;
    String movieName;
}
