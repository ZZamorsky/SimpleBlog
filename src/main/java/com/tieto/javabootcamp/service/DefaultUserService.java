package com.tieto.javabootcamp.service;

import com.tieto.javabootcamp.dao.UserDao;
import com.tieto.javabootcamp.exception.DatabaseException;
import com.tieto.javabootcamp.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserService.class);

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(String name) {
        try {
            userDao.saveUser(new User(name));
        } catch (DatabaseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String name) {
        User user = null;
        try {
            user =  userDao.getUser(name);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean removeUser(String name) {

        boolean isRemoved = false;

        try {
            userDao.deleteUser(name);
            isRemoved = true;
        } catch (DatabaseException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

        return isRemoved;
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDao.getUsersByName(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void authenticateUser() {
        // Not implemented yet
    }
}
