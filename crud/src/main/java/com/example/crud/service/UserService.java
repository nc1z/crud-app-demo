package com.example.crud.service;

import com.example.crud.dao.UserDao;
import com.example.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(@Qualifier("postgres") UserDao userDao) {
        this.userDao = userDao;
    }

    public String addUser(User user) {
        return userDao.insertUser(user);
    }

    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    public Optional<User> getUserById(UUID id) {
        return userDao.selectUserById(id);
    }

    public String deleteUserById(UUID id) {
        return userDao.deleteUserById(id);
    }

    public String updateUserById(UUID id, User user) {
        return userDao.updateUserById(id, user);
    }
}
