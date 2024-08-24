package com.watchwise.watchwise.api.response;

import java.util.List;

import com.watchwise.watchwise.api.model.Movie;

public class MovieResponse {
    
    private int page;
    private List<Movie> results;

    public List<Movie> getresults() {
        return results;
    }

    public void setresults(List<Movie> results) {
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "results=" + results +
                '}';
    }

}
