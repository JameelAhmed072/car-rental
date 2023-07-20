package org.carrental.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.carrental.dao.UserDAO;
import org.carrental.domain.User;

public class AuthenticationService {

    private final UserDAO userDAO = new UserDAO();
    public Boolean checkLogin(String username, String password){
       User user =  userDAO.getUserByUsernameAndPassword(username,password);

       if(user != null){
           return Boolean.TRUE;
       }
       return Boolean.FALSE;
    }
}
