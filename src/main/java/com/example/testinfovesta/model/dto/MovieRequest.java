package com.example.testinfovesta.model.dto;

import java.util.List;

public class MovieRequest {
    private String title;
    private String director;
    private String summary;
    private List<Integer> genresIds;

    @Override
    public String toString() {
        return "MovieRequest{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", summary='" + summary + '\'' +
                ", genresIds=" + genresIds +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Integer> getGenresIds() {
        return genresIds;
    }

    public void setGenresIds(List<Integer> genresIds) {
        this.genresIds = genresIds;
    }
}
