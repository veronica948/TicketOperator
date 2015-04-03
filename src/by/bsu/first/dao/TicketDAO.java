package by.bsu.first.dao;

import by.bsu.first.entity.Ticket;
import by.bsu.first.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Пользователь on 30.11.2014.
 */
public class TicketDAO extends AbstractDAO {
    public static final String SQL_INSERT_TICKET = "INSERT INTO ticket (seanceId,placeId) VALUES (?,?)";
    public static final String SQL_FIND_TICKETS_BY_SEANCE_ID = "SELECT ticketId,row,place,price FROM ticket JOIN place ON ticket.placeId = place.placeId" +
            " JOIN seance ON ticket.seanceId = seance.seanceId WHERE seance.seanceId = ? AND ticketId NOT IN (SELECT ticketId FROM ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId \n" +
            "= orderStatus.orderStatusId WHERE orderStatus LIKE 'Забронирован' OR orderStatus LIKE 'Получен')";

    public static final String SQL_FIND_TICKET_BY_ID = "SELECT row,place,price FROM ticket JOIN place ON ticket.placeId = place.placeId" +
            " JOIN seance ON ticket.seanceId = seance.seanceId WHERE ticketId = ?";
    public TicketDAO(Connection connection) {
        super(connection);
    }

    public void insertTickets(int seanceId, ArrayList<Integer> placesId) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_TICKET);
            for(Integer placeId: placesId) {
                ps.setInt(1, seanceId);
                ps.setInt(2, placeId);
                ps.addBatch();
            }
            ps.executeBatch();
        }
        catch(SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public ArrayList<Ticket> findTicketsBySeanceId(int seanceId) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Ticket> tickets = new ArrayList<Ticket>();
        Ticket ticket = null;
        int row, place, price, ticketId;
          try {
              ps = connection.prepareStatement(SQL_FIND_TICKETS_BY_SEANCE_ID);
              ps.setInt(1,seanceId);
              rs = ps.executeQuery();
              while (rs.next()) {
                  ticketId = rs.getInt(1);
                  row = rs.getInt(2);
                  place = rs.getInt(3);
                  price = rs.getInt(4);
                  ticket = new Ticket(ticketId,row,place,price);
                  tickets.add(ticket);
              }
          } catch (SQLException e) {
              throw new DAOException("SQL exception (request or table failed):",e);
          } finally {
              close(ps);
          }
        return tickets;
    }
    public Ticket findTicketById(int ticketId) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ticket ticket = null;
        int row, place, price;
        try {
            ps = connection.prepareStatement(SQL_FIND_TICKET_BY_ID);
            ps.setInt(1,ticketId);
            rs = ps.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
                place = rs.getInt(2);
                price = rs.getInt(3);
                ticket = new Ticket(ticketId,row,place,price);
            }
        } catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        } finally {
            close(ps);
        }
        return ticket;
    }
}
