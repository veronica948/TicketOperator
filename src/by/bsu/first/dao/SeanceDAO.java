package by.bsu.first.dao;

import by.bsu.first.entity.Seance;
import by.bsu.first.exception.DAOException;
import java.sql.*;
import java.util.ArrayList;

public class SeanceDAO extends AbstractDAO {
    public static final String SQL_SELECT_ALL_SEANCES = "SELECT seanceId, seance.movieId,movieName, seanceDate, seanceTime, price " +
            "FROM seance JOIN movie ON seance.movieId = movie.movieId WHERE seanceDate >= ?" +
                    "ORDER BY seanceDate, seanceTime";
    public static final String SQL_FIND_SEANCES_BY_MOVIE_NAME_AND_DATE = "SELECT seance.movieId,movieName,seanceId,seanceDate,seanceTime, price " +
            "FROM seance JOIN movie ON seance.movieId = movie.movieId WHERE movieName LIKE ? AND seanceDate = ?";
    public static final String SQL_FIND_SEANCES_BY_MOVIE_NAME = "SELECT seance.movieId,movieName,seanceId,seanceDate,seanceTime, price " +
            "FROM seance JOIN movie ON seance.movieId = movie.movieId WHERE movieName LIKE  ? AND seanceDate >= ?";
    public static final String SQL_FIND_SEANCES_BY_DATE = "SELECT seance.movieId,movieName,seanceId,seanceDate,seanceTime, price " +
            "FROM seance JOIN movie ON seance.movieId = movie.movieId WHERE seanceDate = ?";
    public static final String SQL_FIND_SEANCES_BY_MOVIE_ID =
            "SELECT seanceId, seanceDate, seanceTime, price FROM seance WHERE movieId = ? AND seanceDate >= ?";
    public static final String SQL_INSERT_SEANCE = "INSERT INTO seance (movieId,seanceDate,seanceTime,price) " +
            "VALUES(?,?,?,?)";
    public static final String SQL_FIND_SEANCE_ID =
            "SELECT seanceId FROM seance WHERE seanceDate = ? AND seanceTime = ?";
    public static final String SQL_FIND_SEANCE_BY_TICKET_ID =
            "SELECT seance.seanceId, seanceDate, seanceTime, price, movieName, movie.movieId FROM seance JOIN ticket ON seance.seanceId = ticket.seanceId " +
                    "JOIN movie ON seance.movieId = movie.movieId WHERE ticketId = ?";
    public static final String SQL_SELECT_ORDERS_BY_SEANCE_ID = "SELECT orderId FROM ticketOrder " +
            "JOIN ticket ON ticketOrder.ticketId = ticket.ticketId JOIN seance ON ticket.seanceId = seance.seanceId " +
            " WHERE seance.seanceId = ?";
    public static final String SQL_DELETE_SEANCE= "DELETE FROM seance WHERE seanceId = ?";
    public static final String SQL_GET_DATA_AND_TIME_BY_ID = "SELECT seanceDate, seanceTime FROM seance WHERE seanceId = ? ";

    public SeanceDAO(Connection connection) {
        super(connection);
    }
    public ArrayList<Seance> findSeancesByMovieId(int movieId, Date currentDate) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seance> seances = new ArrayList<Seance>();
        Seance seance = null;
        int seanceId;
        Date date;
        Time time;
        int price;
        try {
            ps = connection.prepareStatement(SQL_FIND_SEANCES_BY_MOVIE_ID);
            ps.setInt(1,movieId);
            ps.setDate(2,currentDate);
            rs = ps.executeQuery();
            while (rs.next()) {
                seanceId = rs.getInt(1);
                date = rs.getDate(2);
                time = rs.getTime(3);
                price = rs.getInt(4);
                seance = new Seance(seanceId,movieId,date,time,price);
                seances.add(seance);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seances;
    }
    public ArrayList<Seance> findSeancesByMovieNameAndDate(String movieName, Date date) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seance> seances = new ArrayList<Seance>();
        Seance seance = null;
        int seanceId;
        Time time;
        int price;
        int movieId;
        try {

           ps = connection.prepareStatement(SQL_FIND_SEANCES_BY_MOVIE_NAME_AND_DATE);
            ps.setString(1,movieName);
            ps.setDate(2, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                movieId = rs.getInt(1);
                movieName = rs.getString(2);
                seanceId = rs.getInt(3);
                time = rs.getTime(5);
                price = rs.getInt(6);
                seance = new Seance(seanceId,movieId,date,time,price);
                seance.setMovieName(movieName);
                seances.add(seance);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seances;
    }
    public ArrayList<Seance> findSeancesByDate(Date date) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seance> seances = new ArrayList<Seance>();
        Seance seance = null;
        int seanceId;
        Time time;
        int price;
        int movieId;
        Date dateValue;
        String movieName;
        try {
            ps = connection.prepareStatement(SQL_FIND_SEANCES_BY_DATE);
            ps.setDate(1, date);
            rs = ps.executeQuery();
            while (rs.next()) {
                movieId = rs.getInt(1);
                movieName = rs.getString(2);
                seanceId = rs.getInt(3);
                dateValue = rs.getDate(4);
                time = rs.getTime(5);
                price = rs.getInt(6);
                seance = new Seance(seanceId,movieId,dateValue,time,price);
                seance.setMovieName(movieName);
                seances.add(seance);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seances;
    }
    public ArrayList<Seance> findSeancesByMovieName(String movieName, Date date) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seance> seances = new ArrayList<Seance>();
        Seance seance = null;
        int seanceId;
        Time time;
        int price;
        int movieId;
        Date dateValue;
        try {
             ps = connection.prepareStatement(SQL_FIND_SEANCES_BY_MOVIE_NAME);
             ps.setString(1,movieName);
             ps.setDate(2,date);
            rs = ps.executeQuery();
            while (rs.next()) {
                movieId = rs.getInt(1);
                movieName = rs.getString(2);
                seanceId = rs.getInt(3);
                dateValue = rs.getDate(4);
                time = rs.getTime(5);
                price = rs.getInt(6);
                seance = new Seance(seanceId,movieId,dateValue,time,price);
                seance.setMovieName(movieName);
                seances.add(seance);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seances;
    }
    public void insertSeance(int movieId, Date date, Time time, int price) throws DAOException{
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_SEANCE);
            ps.setInt(1, movieId);
            ps.setDate(2, date);
            ps.setTime(3, time);
            ps.setInt(4, price);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public int findSeanceId(Date date, Time time) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = connection.prepareStatement(SQL_FIND_SEANCE_ID);
            ps.setDate(1, date);
            ps.setTime(2, time);
            rs = ps.executeQuery();
            if(rs.next()) {
                id = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return id;
    }
    public ArrayList<Seance> findAllSeances(Date date) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Seance> seances = new ArrayList<Seance>();
        Seance seance = null;
        int seanceId;
        int movieId;
        String movieName;
        Date seanceDate;
        Time time;
        int price;
        try {
            ps = connection.prepareStatement(SQL_SELECT_ALL_SEANCES);
            ps.setDate(1,date);
            rs = ps.executeQuery();
            while (rs.next()) {
                seanceId = rs.getInt(1);
                movieId = rs.getInt(2);
                movieName = rs.getString(3);
                seanceDate = rs.getDate(4);
                time = rs.getTime(5);
                price = rs.getInt(6);
                seance = new Seance(seanceId,movieId,seanceDate,time,price);
                seance.setMovieName(movieName);
                seances.add(seance);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seances;
    }
    public Seance findSeanceByTicketId(int ticketId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Seance seance = null;
        int seanceId;
        Date date;
        Time time;
        int price;
        String movieName;
        int movieId;
        try {
            ps = connection.prepareStatement(SQL_FIND_SEANCE_BY_TICKET_ID);
            ps.setInt(1,ticketId);
            rs = ps.executeQuery();
            if (rs.next()) {
                seanceId = rs.getInt(1);
                date = rs.getDate(2);
                time = rs.getTime(3);
                price = rs.getInt(4);
                movieName = rs.getString(5);
                movieId = rs.getInt(6);
                seance = new Seance(seanceId,movieId,date,time,price);
                seance.setMovieName(movieName);
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return seance;
    }
    public boolean existOrders(int seanceId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_SELECT_ORDERS_BY_SEANCE_ID);
            ps.setInt(1,seanceId);
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
    public void deleteSeance(int seanceId)throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_DELETE_SEANCE);
            ps.setInt(1,seanceId);
            ps.executeUpdate();
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
    }
    public Seance getSeanceDateAndTimeById(int seanceId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Seance seance = null;
        try {
            ps = connection.prepareStatement(SQL_GET_DATA_AND_TIME_BY_ID);
            ps.setInt(1,seanceId);
            rs = ps.executeQuery();
            Date date =  null;
            Time time = null;
            if(rs.next()) {
                date = rs.getDate(1);
                time = rs.getTime(2);
                seance = new Seance(seanceId,date,time);
            }
            return seance;
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
    }
}
