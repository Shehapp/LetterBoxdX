package com.modelAPI;

import com.DTO.MoviePreviewDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Setter
@Getter
@ToString
public class MoviePreviewApiModel {
        MoviePreviewDTO[] result;
}

