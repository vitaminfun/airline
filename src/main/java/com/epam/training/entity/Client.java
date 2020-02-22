package com.epam.training.entity;

import java.util.Objects;

/**
 * This class discribes Client
 */
public class Client {
    private int id;
    private String firstName;
    private String lastName;
    private int id_ticket;
    private boolean vip;
    private boolean lugagge;

    public Client() {

    }

    public Client(String firstName, String lastName, int id_ticket, boolean vip, boolean lugagge) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.id_ticket = id_ticket;
        this.vip = vip;
        this.lugagge = lugagge;
    }

    public Client(int id, String firstName, String lastName, int id_ticket, boolean vip, boolean lugagge) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id_ticket = id_ticket;
        this.vip = vip;
        this.lugagge = lugagge;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
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

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isLugagge() {
        return lugagge;
    }

    public void setLugagge(boolean lugagge) {
        this.lugagge = lugagge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                id_ticket == client.id_ticket &&
                vip == client.vip &&
                lugagge == client.lugagge &&
                Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, id_ticket, vip, lugagge);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", id_ticket=" + id_ticket +
                ", vip=" + vip +
                ", lugagge=" + lugagge +
                '}';
    }
}
