package by.bsu.first.logic;

import by.bsu.first.dao.MovieDAO;
import by.bsu.first.dao.OrderDAO;
import by.bsu.first.dao.TicketDAO;
import by.bsu.first.entity.Movie;
import by.bsu.first.entity.Order;
import by.bsu.first.entity.Ticket;
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
 * Created by Пользователь on 03.12.2014.
 */
public class TicketLogic {
    public static ArrayList<Ticket> getTicketsBySeanceId(int seanceId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            TicketDAO dao = new TicketDAO(cn);
            ArrayList<Ticket> tickets = dao.findTicketsBySeanceId(seanceId);
            return tickets;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static Ticket getTicketById(int ticketId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            TicketDAO dao = new TicketDAO(cn);
            Ticket ticket = dao.findTicketById(ticketId);
            return ticket;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }
}
