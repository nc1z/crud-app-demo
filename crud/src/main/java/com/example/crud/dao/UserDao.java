package com.example.crud.dao;

import com.example.crud.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao {

    // CREATE - Adding new user to DB with id
    String insertUser(UUID id, User user);

    // DEFAULT CREATE - Adding new user to DB without id
    default String insertUser(User user) {
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }

    // GET - Retrieve all user data from DB
    List<User> getAllUsers();

    // SELECT - Retrieve specific user data from DB
    Optional<User> selectUserById(UUID id);

    // UPDATE - Update / replace specific user data from DB
    String updateUserById(UUID id, User user);

    // DELETE - Delete specific user data from DB
    String deleteUserById(UUID id);
}
