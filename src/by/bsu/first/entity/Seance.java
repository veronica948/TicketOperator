package by.bsu.first.entity;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by Пользователь on 11.11.2014.
 */
public class Seance {
    private int seanceId;
    private int movieId;
    private Date date;
    private Time time;
    private int price;
    private String movieName;

    public Seance(int seanceId, Date date, Time time) {
        this.seanceId = seanceId;
        this.date = date;
        this.time = time;
    }

    public Seance(int seanceId, int movieId, Date date, Time time, int price) {
        this.seanceId = seanceId;
        this.movieId = movieId;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSeanceId() {
        return seanceId;
    }

    public void setSeanceId(int seanceId) {
        this.seanceId = seanceId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
