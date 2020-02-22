package com.epam.training.dao;

import com.epam.training.entity.Client;

public interface ClientDAO extends GenericDAO<Client> {
    Client getClientByName(String firstName, String lastName);

    Client getClientById(int id);
}
