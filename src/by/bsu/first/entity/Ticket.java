package by.bsu.first.entity;

/**
 * Created by Пользователь on 03.12.2014.
 */
public class Ticket {
    private int ticketId;

    private int row;
    private int place;
    private int price;



    public Ticket(int ticketId, int row, int place, int price) {
        this.ticketId = ticketId;
        this.row = row;
        this.place = place;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
