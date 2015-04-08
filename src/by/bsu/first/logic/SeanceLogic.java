package by.bsu.first.logic;

import by.bsu.first.dao.MovieDAO;
import by.bsu.first.dao.PlaceDAO;
import by.bsu.first.dao.SeanceDAO;
import by.bsu.first.dao.TicketDAO;
import by.bsu.first.entity.Seance;
import by.bsu.first.exception.DAOException;
import by.bsu.first.exception.LogicException;
import by.bsu.first.exception.PoolConnectionException;
import by.bsu.first.pool.Pool;
import by.bsu.first.validation.Validation;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by Пользователь on 24.11.2014.
 */
public class SeanceLogic {

    public static ArrayList<Seance> getSeancesByDateAndMovie(String movieName, String dateValue)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO seanceDAO = new SeanceDAO(cn);
            ArrayList<Seance> seances = null;
            if (Validation.isEmpty(movieName)) {
                if (Validation.isDate(dateValue)) {
                    seances = seanceDAO.findSeancesByDate(Date.valueOf(dateValue));
                }
            } else {
                if (Validation.isEmpty(dateValue)) {
                    seances = seanceDAO.findSeancesByMovieName(movieName, Date.valueOf(LocalDate.now()));
                } else {
                    if (Validation.isDate(dateValue)) {
                        seances = seanceDAO.findSeancesByMovieNameAndDate(movieName, Date.valueOf(dateValue));
                    }
                }
            }
            return seances;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static ArrayList<Seance> getAllSeances()
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO seanceDAO = new SeanceDAO(cn);
            ArrayList<Seance> seances = seanceDAO.findAllSeances(Date.valueOf(LocalDate.now()));
            return seances;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void addSeance(String movieName, Date date, Time time, int price) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO seanceDAO = new SeanceDAO(cn);
            MovieDAO movieDAO = new MovieDAO(cn);
            int movieId = movieDAO.findMovieIdByName(movieName);
            seanceDAO.insertSeance(movieId, date, time, price);
            PlaceDAO placeDAO = new PlaceDAO(cn);
            TicketDAO ticketDAO = new TicketDAO(cn);
            ArrayList<Integer> places = placeDAO.getAllPlaces();
            int seanceId = seanceDAO.findSeanceId(date, time);
            ticketDAO.insertTickets(seanceId,places);
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static Seance getSeanceByTicketId(int ticketId)
            throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO seanceDAO = new SeanceDAO(cn);
            Seance seance = seanceDAO.findSeanceByTicketId(ticketId);
            return seance;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static boolean existOrders(int seanceId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO dao = new SeanceDAO(cn);
            return dao.existOrders(seanceId);
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void deleteSeance(int seanceId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO dao = new SeanceDAO(cn);
            dao.deleteSeance(seanceId);
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static boolean isOldSeance(int seanceId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO dao = new SeanceDAO(cn);
            Seance seance = dao.getSeanceDateAndTimeById(seanceId);
            if (seance == null) {
                return false;
            }
            Date date = seance.getDate();
            LocalDate localDate = date.toLocalDate();
            Time time = seance.getTime();
            LocalTime timeNow = LocalTime.now();
            LocalDate dateNow = LocalDate.now();
            LocalTime localTime = time.toLocalTime();
            if (localDate.isBefore(dateNow)) {
                return true;
            }
            if (localDate.isAfter(dateNow)) {
                return false;
            }
            if (localTime.isBefore(timeNow)) {
                return true;
            }
            return false;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }
    public static boolean existsSeanceTime(Date date, Time time) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            SeanceDAO dao = new SeanceDAO(cn);
            return dao.existsSeance(date,time);
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }
}
