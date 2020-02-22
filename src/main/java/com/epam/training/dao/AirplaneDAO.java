package com.epam.training.dao;

import com.epam.training.entity.Airplane;

public interface AirplaneDAO extends GenericDAO<Airplane> {
    Airplane getAirplaneById(int id);
    void deleteAirplaneById(int id);
    Airplane getAirplaneByName(String name);
}
