package com.example.crud.dao;

import com.example.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgres")
public class UserDataAccessService implements UserDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String insertUser(UUID id, User user) {
        // UUID has already been generated automatically in UserDao interface
        // (If there isn't already a provided UUID)
        final String sql = "INSERT INTO person (id, name) VALUES (?, ?)";
        int create = jdbcTemplate.update(sql, id, user.getName());
        if (create == 1) {
            System.out.println("Created Record with ID = " + id);
            return "Created Record with ID = " + id;
        }
        System.out.println("Error: Failed to create record");
        return "Error";
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "SELECT id, name FROM person";
        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new User(id , name);
        });
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        final String sql = "SELECT id, name FROM person WHERE id = ?";
        User user = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID userId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return new User(userId , name);
        });
        return Optional.ofNullable(user);
    }

    @Override
    public String updateUserById(UUID id, User user) {
        final String sql = "UPDATE person SET id = uuid_generate_v4(), name = ? WHERE id = ?";
        int update = jdbcTemplate.update(sql, user.getName(), id);
        if (update == 1) {
            System.out.println("Replaced Record with ID = " + id);
            return "Replaced Record with ID = " + id;
        }
        System.out.println("Error: Failed to update record");
        return "Error";
    }

    @Override
    public String deleteUserById(UUID id) {
        final String sql = "DELETE FROM person WHERE id = ?";
        Object[] args = new Object[]{id};
        int delete = jdbcTemplate.update(sql, args);
        if (delete == 1) {
            System.out.println("Deleted Record with ID = " + id);
            return "Deleted Record with ID = " + id;
        }
        System.out.println("Error: Failed to delete record");
        return "Error";
    }

}
