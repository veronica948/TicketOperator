package by.bsu.first.logic;

import by.bsu.first.dao.OrderDAO;
import by.bsu.first.entity.Order;
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
 * Created by Пользователь on 07.12.2014.
 */
public class OrderLogic {
    private static final String ORDER_STATUS = "Забронирован";

    public static boolean existOrder(int ticketId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            return dao.existOrderByTicketId(ticketId, ORDER_STATUS);
        } catch (PoolConnectionException e) {
            throw new LogicException(e.getCause());
        } catch (DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static int makeOrder(int ticketId, int userId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            Date date = Date.valueOf(LocalDate.now());
            dao.insert(ticketId, userId, date);
            int id = dao.findOrderId(ticketId, userId, date);
            return id;
        } catch (PoolConnectionException e) {
            throw new LogicException(e.getCause());
        } catch (DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static Order findOrderById(int orderId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            Order order = dao.findOrderById(orderId);
            return order;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static void changeOrderStatus(int orderId, String status) throws LogicException {
        final String STATUS = "Получен";
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            if (status.equals(STATUS)) {
                dao.changeOrderStatusAndDate(orderId, status, Date.valueOf(LocalDate.now()));
            } else {
                dao.changeOrderStatus(orderId, status);
            }
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static ArrayList<Order> findOrdersByUserId(int userId) throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            ArrayList<Order> orders = dao.findOrdersByUserId(userId);
            return orders;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

    public static ArrayList<Order> findOrders() throws LogicException {
        Connection cn = null;
        try {
            cn = Pool.getPool().getConnection();
            OrderDAO dao = new OrderDAO(cn);
            ArrayList<Order> orders = dao.findOrdersByStatus(ORDER_STATUS);
            return orders;
        } catch (PoolConnectionException | DAOException e) {
            throw new LogicException(e.getCause());
        } finally {
            Pool.getPool().returnConnection(cn);
        }
    }

}
