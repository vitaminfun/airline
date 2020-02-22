package com.epam.training.helperAuth;

import com.epam.training.dao.DAOFactory;
import com.epam.training.dao.UserDAO;
import com.epam.training.entity.User;

import java.util.ArrayList;
import java.util.List;

public class HelperReg {
    private User userFromJson;

    public HelperReg(User user) {
        this.userFromJson = user;
    }

    public String addUser(){
        DAOFactory jdbcDaoFactory= DAOFactory.getDAOFactory();
        UserDAO userDAO = jdbcDaoFactory.getUserDAO();
        List<User> users=new ArrayList<>();
        users = userDAO.findAll();
        String result=null;
        if (users.isEmpty()){
            userDAO.create(userFromJson);
            return "Successful Registration";
        } else {
                for (User existUser:users) {
                    if (existUser.getLogin().equals(userFromJson.getLogin())){
                        return "The username or password you entered is incorrect";
                    }
                }
                    userDAO.create(userFromJson);
                    return "Successful Registration";
        }

    }
}
