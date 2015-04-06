package by.bsu.first.logic;

import by.bsu.first.dao.MovieDAO;
import by.bsu.first.dao.SeanceDAO;
import by.bsu.first.entity.Movie;
import by.bsu.first.entity.Seance;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.exception.PoolConnectionException;
import by.bsu.first.pool.Pool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by Пользователь on 18.11.2014.
 */
public class MovieLogic {

    public static ArrayList<Movie> getAllMovies() throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            ArrayList<Movie> movies = dao.findAllMovies();
            return movies;
        } catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static ArrayList<String> getAllMovieNames() throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            ArrayList<String> movieNames = dao.findAllMovieNames();
            return movieNames;
        } catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static Movie getMovieById(int id) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO movieDAO = new MovieDAO(cn);
            Movie movie = movieDAO.findMovieById(id);
            return movie;
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static Movie getMovieByName(String movieName) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO movieDAO = new MovieDAO(cn);
            Movie movie = movieDAO.findMovieByMovieName(movieName);
            return movie;
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static ArrayList<Seance> getSeancesByMovieId(int id) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO seanceDAO = new SeanceDAO(cn);
            ArrayList<Seance> seances = seanceDAO.findSeancesByMovieId(id, Date.valueOf(LocalDate.now()));
            return seances;
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void insertMovie(String movieName, String description, String country, String actors, Date releaseDate) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            dao.insertMovie(movieName, description, country, actors, releaseDate);
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void deleteMovie(String movieName) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            dao.deleteMovie(movieName);
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void changeMovie(int id, String movieName, String description, String country, String actors, Date releaseDate)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            dao.changeMovie(id, movieName, description, country, actors, releaseDate);
        }  catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static boolean existOrders(String movieName) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            return dao.existOrders(movieName);
        } catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }
    public static boolean isBeforeReleaseDate(Date seanceDate,String movieName) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            MovieDAO dao = new MovieDAO(cn);
            Date date = dao.findReleaseDateByMovieName(movieName);
            if(date == null) {
                return false;
            } else {
                return seanceDate.toLocalDate().isBefore(date.toLocalDate());
            }
        } catch (PoolConnectionException |DAOException e) {
            throw new LogicException(e.getCause());
        }  finally {
            Pool.getPool().returnConnection(cn);
        }
    }
}
