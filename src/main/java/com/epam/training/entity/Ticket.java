package com.epam.training.entity;

import java.util.Objects;

/**
 * This class discribes Ticket
 */
public class Ticket {
    private int idTicket;
    private int idFlight;
    private int seat;
    private double cost;

    public Ticket() {

    }

    public Ticket(int idFlight, int seat, double cost) {
        this.idTicket = idTicket;
        this.idFlight = idFlight;
        this.seat = seat;
        this.cost = cost;
    }

    public Ticket(int idTicket, int idFlight, int seat, double cost) {
        this.idTicket = idTicket;
        this.idFlight = idFlight;
        this.seat = seat;
        this.cost = cost;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "idTicket=" + idTicket +
                ", idFlight=" + idFlight +
                ", seat=" + seat +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return idTicket == ticket.idTicket &&
                idFlight == ticket.idFlight &&
                seat == ticket.seat &&
                Double.compare(ticket.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTicket, idFlight, seat, cost);
    }
}
