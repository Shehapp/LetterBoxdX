package com.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MovieStateDTO {
    boolean isWatched;
    boolean isWatchList;
    boolean isFavorite;

}
