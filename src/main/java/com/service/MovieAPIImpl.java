package com.service;

import com.DTO.MoviePreviewDTO;
import com.modelAPI.MovieApiModel;
import com.modelAPI.MoviePreviewApiModel;
import com.DTO.MovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("classpath:api.properties")
public class MovieAPIImpl implements MovieAPI {
    @Autowired
    RestTemplate restTemplate;
    @Value("${movie.apiKey}")
    String apiKey;
    @Value("${movie.name.url}")
    String urlName;
    @Value("${movie.id.url}")
    String urlId;

    @Override
    public List<MoviePreviewDTO> getMovies(String movieName) {

        movieName= validateSearchString(movieName);


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> params = Map.of("movieName", movieName);
        URI uri = UriComponentsBuilder.fromUriString(urlName)
                .build(params);

        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<MoviePreviewApiModel> responseEntity = restTemplate.exchange(requestEntity, MoviePreviewApiModel.class);

        return List.of(responseEntity.getBody().getResult());
    }

    @Override
    public MovieDTO getMovie(String movieId) {

        Map<String, String> params = Map.of("movieId", movieId);
        URI uri = UriComponentsBuilder.fromUriString(urlId)
                .build(params);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<Void> requestEntity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<MovieApiModel> responseEntity = restTemplate.exchange(requestEntity, MovieApiModel.class);
        return  responseEntity.getBody().getResult();
    }

    @Override
    public String validateSearchString(String searchString) {
        searchString= searchString.trim();
        searchString = searchString.replaceAll(" ", "+");
        return searchString;
    }
}
