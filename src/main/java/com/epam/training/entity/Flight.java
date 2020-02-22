package com.epam.training.entity;

import java.util.Date;
import java.util.Objects;

/**
 * This class discribes Flight
 */
public class Flight {
    private int idFlight;
    private int idAirplane;
    private String from;
    private String to;
    private Date date;

    public Flight() {

    }

    public Flight(int idAirplane, String from, String to, Date date) {

        this.idAirplane = idAirplane;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public Flight(int idFlight, int idAirplane, String from, String to, Date date) {
        this.idFlight = idFlight;
        this.idAirplane = idAirplane;
        this.from = from;
        this.to = to;
        this.date = date;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public void setIdFlight(int idFlight) {
        this.idFlight = idFlight;
    }

    public int getIdAirplane() {
        return idAirplane;
    }

    public void setIdAirplane(int idAirplane) {
        this.idAirplane = idAirplane;
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

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return idFlight == flight.idFlight &&
                Objects.equals(from, flight.from) &&
                Objects.equals(to, flight.to) &&
                Objects.equals(date, flight.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFlight, from, to, date);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "idFlight=" + idFlight +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", date=" + date +
                '}';
    }


}
