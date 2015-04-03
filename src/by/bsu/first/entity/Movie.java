package by.bsu.first.entity;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Пользователь on 10.11.2014.
 */
public class Movie {
    private int id;
    private String movieName;
    private String description;
    private String country;
    String actors;
    Date releaseDate;
    public Movie(int id, String movieName) {
        this.id = id;
        this.movieName = movieName;
    }

    public Movie(int id, String movieName, String description, String country, String actors, Date releaseDate) {
        this.id = id;
        this.movieName = movieName;
        this.description = description;
        this.country = country;
        this.actors = actors;
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieName() {
        return movieName;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return movieName;
    }
}
