package com.epam.training.dao;

import com.epam.training.entity.Ticket;

public interface TicketDAO extends GenericDAO<Ticket> {

    Ticket getTicketById(int id);

    Ticket getTicketByIdFlight(int idFlight);

    Ticket getTicketBySeat(int seat);

    int createTicket(int idFlight, int seat, double cost);
}
