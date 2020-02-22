package com.epam.training.dao;

import com.epam.training.entity.Ticket;
import com.epam.training.entity.User;

public interface UserDAO extends GenericDAO<User> {

    User getUserById(int id);

    User getUserByLogin(String login);

    User getUserByFirstName(String firstName);

    User getUserByLastName(String firstName);

    void deleteUserById(int id);
}
