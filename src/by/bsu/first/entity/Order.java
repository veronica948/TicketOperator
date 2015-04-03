package by.bsu.first.entity;

import java.sql.Date;

/**
 * Created by Пользователь on 07.12.2014.
 */
public class Order {
    private int orderId;
    private int ticketId;
    private int userId;
    private Date orderDate;
    private String orderStatus;
    private String login;
    private String lastname;

    public Order(int orderId, int ticketId,int userId, Date orderDate, String orderStatus) {
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderId, String login, String lastname, Date orderDate, String orderStatus) {
        this.orderId = orderId;
        this.login = login;
        this.lastname = lastname;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderId, Date orderDate, String orderStatus) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
