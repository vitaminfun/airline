package com.epam.training.helperAuth;

import com.epam.training.entity.User;

public class HelperAuth {
    private User user;

    public HelperAuth(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String checkUser(User userFromDB){
        if (userFromDB==null) {
            return "The username or password you entered is incorrect";
        }else{
            if (user.getLogin().equals(userFromDB.getLogin()) && user.getPassword().equals(userFromDB.getPassword())){
                return "Successful";
            }
        }
        return "The username or password you entered is incorrect";
    }

}
