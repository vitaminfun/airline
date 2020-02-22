package com.epam.training.logic;

import com.epam.training.dao.*;
import com.epam.training.dao.jdbc.JdbcClientDAO;
import com.epam.training.entity.Airplane;
import com.epam.training.entity.Client;
import com.epam.training.entity.Flight;
import com.epam.training.entity.Ticket;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This class discribes Order Processing
 */
public class OrderProcessor {
    private static final Logger log= LogManager.getLogger(JdbcClientDAO.class);

    /**
     *according to the generated order, the search for the desired flight is carried out.
     * If the specified flight is located, then an object of class Ticket and class Client is created
     * @param order-received order
     */
    public Client processOrder(Order order){
        Client client=null;
        try {
            Flight flight=searchFlight(order);
            int ticketId = createTicket(flight.getIdFlight(), setSeat(flight.getIdAirplane()), calculateCost(order));
            client=createClient(order, ticketId);
        } catch (NullPointerException e) {
            log.info("the specified flight is missing",e);
            e.printStackTrace();
        }
        return client;

    }

    /**
     *
     * @param order-received order
     * @param ticketId-client ticket id
     */
    private Client createClient(Order order, int ticketId) {
        Client client = new Client( order.getFirstName(), order.getLastName(),ticketId,order.isVip(),order.isLuggage());
        DAOFactory jdbcDaoFactory=  DAOFactory.getDAOFactory();
        ClientDAO clientDAO=jdbcDaoFactory.getClientDAO();
        clientDAO.create(client);
        return client;


    }

    public Flight searchFlight(Order order){
        Flight fs=null;
        try {
            DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
            FlightDAO flightDAO = jdbcDaoFactory.getFlightDAO();
            fs=flightDAO.getFlight(order.getFrom(), order.getTo(), order.getDate());
        } catch (NullPointerException e) {
            log.info("the specified flight is missing");
            e.printStackTrace();
        }
        return fs;
    }

    /**
     * calculation of cost depending on the right of the first order, date of departure, baggage
     * @param order
     * @return cost Ticket
     */
    public double calculateCost(Order order){
        double minCost=100;
        double intemediateCost=0;
        if(order.isVip()){
            intemediateCost=minCost+(minCost*0.25);
        }else{
            intemediateCost=minCost;
        }

        if (order.isLuggage()) {
            intemediateCost = minCost + (minCost * 0.25);
        }else{
                return intemediateCost;
        }
        Date currentDate=new Date();
        double a = TimeUnit.DAYS.convert((currentDate.getTime()),TimeUnit.MILLISECONDS);
        double b = TimeUnit.DAYS.convert((order.getDate().getTime()),TimeUnit.MILLISECONDS);
        double c = a/b;
        intemediateCost=intemediateCost+(intemediateCost* c);
        return intemediateCost;
    }

    /*
    definition and assignment of a place
     */
    public int setSeat(int idAirplane){
        int seat=1;
        DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
        AirplaneDAO airplaneDAO = jdbcDaoFactory.getAirplaneDAO();
        Airplane airplane=airplaneDAO.getAirplaneById(idAirplane);
        int numberOfSeat=airplane.getNumbersOfSeats();
        for (int i = 0; i < numberOfSeat; i++) {
            TicketDAO ticketDAO=jdbcDaoFactory.getTicketDAO();
            Ticket ticket = ticketDAO.getTicketBySeat(seat);
            if (ticket == null) {
                return seat;
            } else {
                seat++;
            }
        }
        log.info("Plane is full");
        return -1;
    }

    /*
     *create ticket with flight, seat and cost
     */
    public int createTicket(int idFlight,int seat, double cost){
        DAOFactory jdbcDaoFactory = DAOFactory.getDAOFactory();
        TicketDAO ticketDAO = jdbcDaoFactory.getTicketDAO();
        return ticketDAO.createTicket(idFlight, seat, cost);
    }
}
