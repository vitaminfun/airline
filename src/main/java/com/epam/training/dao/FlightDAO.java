package com.epam.training.dao;

import com.epam.training.entity.Flight;

import java.util.Date;

public interface FlightDAO extends GenericDAO<Flight> {
    Flight getFlightById(int id);

    Flight getFlightByDate(Date dateFlight);

    Flight getFlight(String from, String to, Date date);

    void deleteFlightById(int id);

}
