package com.epam.training.logic;

import java.util.Date;

/**
 * This class discribes order
 */
public class Order {
    private String firstName;
    private String lastName;
    private String from;
    private String to;
    private boolean vip;
    private boolean luggage;
    private Date date;

    public Order(String firstName, String lastName, String from, String to, boolean vip, boolean luggage, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.from = from;
        this.to = to;
        this.vip = vip;
        this.luggage=luggage;
        this.date = date;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getDate() {
        return date;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isLuggage() {
        return luggage;
    }

    public void setLuggage(boolean luggage) {
        this.luggage = luggage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", vip=" + vip +
                ", luggage=" + luggage +
                ", date=" + date +
                '}';
    }
}
