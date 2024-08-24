package com.watchwise.watchwise.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.watchwise.watchwise.api.model.Movie;
import com.watchwise.watchwise.api.response.MovieResponse;

import java.util.List;

@Service
public class MovieService {

    private final RestTemplate restTemplate;

    private String apiKey="2538e071ac74693976007106c8f58d1e";

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Movie> discoverMovies() {
        String url = "https://api.themoviedb.org/3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc";

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization", "Bearer token");

        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<MovieResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity, MovieResponse.class);

            logger.info("Response Status Code: {}", response.getStatusCode());
            logger.info("Response Body: {}", response.getBody());

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                logger.info("Response Body: {}", response.getBody());
                return response.getBody().getresults();
            } else {
                logger.error("Error fetching movies: Status Code: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            logger.error("Exception occurred while fetching movies: ", e);
            return null;
        }
    }
}
