package by.bsu.first.dao;

import by.bsu.first.exception.DAOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Пользователь on 30.11.2014.
 */
public class PlaceDAO extends AbstractDAO{
    public static final String SQL_SELECT_ALL_PLACES = "SELECT placeId FROM place";
    public PlaceDAO(Connection connection) {
        super(connection);
    }
    public ArrayList<Integer> getAllPlaces() throws DAOException {
        Statement st = null;
        ResultSet rs = null;
        ArrayList<Integer> places = new ArrayList<Integer>();
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SQL_SELECT_ALL_PLACES);
            while (rs.next()) {
               places.add(rs.getInt(1));
            }
        }
        catch (SQLException e) {
            throw new DAOException("SQL exception in function getAllPlaces (request or table failed):",e);
        }
        finally {
            close(st);
        }
        return places;
    }
}
