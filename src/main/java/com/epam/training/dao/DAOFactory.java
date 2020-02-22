package com.epam.training.dao;

import com.epam.training.dao.jdbc.JdbcDAOFactory;

public abstract class DAOFactory {
    public abstract ClientDAO getClientDAO();

    public abstract TicketDAO getTicketDAO();

    public abstract FlightDAO getFlightDAO();

    public abstract AirplaneDAO getAirplaneDAO();

    public abstract UserDAO getUserDAO();



    public static DAOFactory getDAOFactory() {
        return JdbcDAOFactory.getFactory();
    }
}
