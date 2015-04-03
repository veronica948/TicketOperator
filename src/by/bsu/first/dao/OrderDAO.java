package by.bsu.first.dao;

import by.bsu.first.entity.Order;
import by.bsu.first.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Пользователь on 04.12.2014.
 */
public class OrderDAO extends AbstractDAO {
    private static final String SQL_INSERT_ORDER = "INSERT INTO ticketOrder (ticketId, userId,orderDate) VALUES (?, ?, ?)";
    private static final String SQL_FIND_ORDER_ID = "SELECT orderId FROM ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId = orderStatus.orderStatusId" +
            " WHERE ticketId = ? AND userId = ? AND orderDate = ?" +
            "AND orderStatus LIKE 'Забронирован'";
    private static final String SQL_FIND_ORDER_BY_ID = "SELECT ticketId, userId, orderDate, orderStatus " +
            "FROM ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId = orderStatus.orderStatusId WHERE orderId = ?";
    private static final String SQL_CHANGE_STATUS = "UPDATE ticketOrder SET orderStatusId = (SELECT orderStatusId FROM orderStatus WHERE orderStatus LIKE ?)" +
            " WHERE orderId = ?";
    private static final String SQL_CHANGE_STATUS_AND_DATE = "UPDATE ticketOrder SET receivingDate = ?, orderStatusId = (SELECT orderStatusId FROM orderStatus WHERE orderStatus LIKE ?)" +
            " WHERE orderId = ?";
    private static final String SQL_FIND_ORDERS_BY_USER_ID = "SELECT orderId, orderDate, orderStatus " +
            "FROM ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId = orderStatus.orderStatusId WHERE userId = ?";
    private static final String SQL_FIND_ORDERS_BY_STATUS = "SELECT orderId, login, lastname, orderDate FROM ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId = orderStatus.orderStatusId JOIN user ON ticketOrder.userId = user.userId  WHERE orderStatus LIKE ?";
    private static final String SQL_FIND_ORDER_ID_BY_TICKET_ID = "SELECT orderId FROM  ticketOrder JOIN orderStatus ON ticketOrder.orderStatusId = orderStatus.orderStatusId" +
            " WHERE orderStatus LIKE ? AND ticketId = ?";

    public OrderDAO(Connection connection) {
        super(connection);
    }
    public void insert(int ticketId, int userId, Date date) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_INSERT_ORDER);
            ps.setInt(1,ticketId);
            ps.setInt(2,userId);
            ps.setDate(3,date);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed)",e);
        }
        finally {
            close(ps);
        }
    }
    public int findOrderId(int ticketId, int userId, Date date)throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = 0;
        try {
            ps = connection.prepareStatement(SQL_FIND_ORDER_ID);
            ps.setInt(1,ticketId);
            ps.setInt(2,userId);
            ps.setDate(3,date);
            rs = ps.executeQuery();
            if(rs.next()) {
               id = rs.getInt(1);
            }
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return id;
    }
    public Order findOrderById(int orderId) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_ORDER_BY_ID);
            ps.setInt(1,orderId);
            rs = ps.executeQuery();
            if(rs.next()) {
                int ticketId = rs.getInt(1);
                int userId = rs.getInt(2);
                Date orderDate = rs.getDate(3);
                String orderStatus = rs.getString(4);
                order = new Order(orderId,ticketId,userId,orderDate,orderStatus);
            }
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);

        }
        finally {
            close(ps);
        }
        return order;
    }
    public void changeOrderStatus(int orderId, String status) throws DAOException{
         PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_STATUS);
            ps.setString(1,status);
            ps.setInt(2,orderId);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public void changeOrderStatusAndDate(int orderId, String status, Date date) throws DAOException {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(SQL_CHANGE_STATUS_AND_DATE);
            ps.setDate(1,date);
            ps.setString(2,status);
            ps.setInt(3,orderId);
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
    public ArrayList<Order> findOrdersByUserId(int userId) throws DAOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            ps = connection.prepareStatement(SQL_FIND_ORDERS_BY_USER_ID);
            ps.setInt(1,userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                Date orderDate = rs.getDate(2);
                String orderStatus = rs.getString(3);
                order = new Order(orderId,orderDate,orderStatus);
                orders.add(order);
            }
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return orders;
    }
    public ArrayList<Order> findOrdersByStatus(String status) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        ArrayList<Order> orders = new ArrayList<Order>();
        try {
            ps = connection.prepareStatement(SQL_FIND_ORDERS_BY_STATUS);
            ps.setString(1,status);
            rs = ps.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                String login = rs.getString(2);
                String lastname = rs.getString(3);
                Date orderDate = rs.getDate(4);
                order = new Order(orderId,login,lastname,orderDate,status);
                orders.add(order);
            }
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
        return orders;
    }
    public boolean existOrderByTicketId(int ticketId,String orderStatus) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(SQL_FIND_ORDER_ID_BY_TICKET_ID);
            ps.setString(1,orderStatus);
            ps.setInt(2,ticketId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        }catch (SQLException e) {
            throw new DAOException("SQL exception (request or table failed):",e);
        }
        finally {
            close(ps);
        }
    }
}
