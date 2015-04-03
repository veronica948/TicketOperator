package by.bsu.first.dao;

import by.bsu.first.entity.Movie;
import by.bsu.first.exception.DAOException;
import sun.util.calendar.BaseCalendar;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by Пользователь on 10.11.2014.
 */
public class MovieDAO extends AbstractDAO {
    public static final String SQL_SELECT_ALL_MOVIES = "SELECT movieId, movieName,description, country,actors, releaseDate " +
            "FROM movie";
    public static final String SQL_FIND_MOVIE_BY_ID = "SELECT movieName,description,country,actors, releaseDate FROM movie WHERE movieId = ?";
    public static final String SQL_INSERT_MOVIE = "INSERT INTO movie (movieName,description,country,actors, releaseDate) VALUES (?,?,?,?,?)";
    public static final String SQL_CHANGE_MOVIE = "UPDATE movie SET movieName = ?, description = ?, country = ?, actors = ?, releaseDate = ?" +
            " WHERE  movieId = ?";
    public static final String SQL_FIND_MOVIE_ID_BY_MOVIE_NAME =
            "SELECT movieId FROM  movie WHERE movieName LIKE ? ";
    public static final String SQL_DELETE_MOVIE = "DELETE FROM movie WHERE movieName LIKE ?";
    public static final String SQL_FIND_ALL_MOVIE_NAMES = "SELECT movieName from movie";
    public static final String SQL_FIND_MOVIE_BY_MOVIE_NAME = "SELECT movieId,description,country,actors, releaseDate FROM movie WHERE movieName LIKE ?";
    public static final String SQL_SELECT_ORDERS_BY_MOVIE_NAME = "SELECT orderId FROM ticketOrder " +
            "JOIN ticket ON ticketOrder.ticketId = ticket.ticketId JOIN seance ON ticket.seanceId = seance.seanceId " +
            "JOIN movie ON seance.movieId = movie.movieId WHERE movieName LIKE ?";
    public MovieDAO(Connection connection) {
        super(connection);
    }
    public ArrayList<Movie> findAllMovies() throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        Movie movie = null;
        String movieName;
        String description;
        String country;
        String actors;
        Date releaseDate;
        int id;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_MOVIES);
            while (rs.next()) {
                id = rs.getInt(1);
                movieName = rs.getString(2);
                description = rs.getString(3);
                country = rs.getString(4);
                actors = rs.getString(5);
                releaseDate = rs.getDate(6);
                movie = new Movie(id, movieName,description,country,actors,releaseDate);
                movies.add(movie);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
    }
        finally {
            close(st);
        }
        return movies;
    }
    public ArrayList<String> findAllMovieNames() throws DAOException{
        Statement st = null;
        ResultSet rs = null;
        ArrayList<String> movieNames = new ArrayList<String>();
        String movieName;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SQL_FIND_ALL_MOVIE_NAMES);
            while (rs.next()) {
                movieName = rs.getString(1);
                movieNames.add(movieName);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(st);
        }
        return movieNames;
    }
    public Movie findMovieById(int movieId) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie movie = null;
        String movieName;
        String description;
        String country;
        String actors;
        Date date;
        try {
            ps = connection.prepareStatement(SQL_FIND_MOVIE_BY_ID);
            ps.setInt(1, movieId);
            rs = ps.executeQuery();
            if(rs.next()) {
                movieName = rs.getString(1);
                description = rs.getString(2);
                country = rs.getString(3);
                actors = rs.getString(4);
                date = rs.getDate(5);
                movie = new Movie(movieId,movieName,description,country,actors,date);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return movie;
    }
    public void insertMovie(String movieName, String description, String country,String actors, Date releaseDate) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_MOVIE);
            ps.setString(1,movieName);
            ps.setString(2,description);
            ps.setString(3,country);
            ps.setString(4,actors);
            ps.setDate(5, releaseDate);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public int findMovieIdByName(String movieName) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int movieId = 0;
        try {
            ps = connection.prepareStatement(SQL_FIND_MOVIE_ID_BY_MOVIE_NAME);
            ps.setString(1,movieName);
            rs = ps.executeQuery();
            if(rs.next()) {
                movieId = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
        return  movieId;
    }
    public void deleteMovie(String movieName)throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_MOVIE);
            ps.setString(1,movieName);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
    }
    public Movie findMovieByMovieName(String movieName) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Movie movie = null;
        int movieId;
        String description;
        String country;
        String actors;
        Date date;
        try {
            ps = connection.prepareStatement(SQL_FIND_MOVIE_BY_MOVIE_NAME);
            ps.setString(1, movieName);
            rs = ps.executeQuery();
            if(rs.next()) {
                movieId = rs.getInt(1);
                description = rs.getString(2);
                country = rs.getString(3);
                actors = rs.getString(4);
                date = rs.getDate(5);
                movie = new Movie(movieId,movieName,description,country,actors,date);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
        return movie;
    }
    public void changeMovie(int id,String movieName, String description, String country,String actors, Date releaseDate)
        throws DAOException {
         PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_MOVIE);
            ps.setString(1,movieName);
            ps.setString(2,description);
            ps.setString(3,country);
            ps.setString(4,actors);
            ps.setDate(5,releaseDate);
            ps.setInt(6,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("PreparedStatement failed in changing movie",e);
        }
    }
    public boolean existOrders(String movieName) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_ORDERS_BY_MOVIE_NAME);
            ps.setString(1,movieName);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
             throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
    }
}
