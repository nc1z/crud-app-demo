package com.example.crud.controller;

import com.example.crud.model.User;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/user")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public String addUser(@Valid @NonNull @RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @PutMapping(path = "{id}")
    public String updateUserById(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User newUser) {
        return userService.updateUserById(id, newUser);
    }

    @DeleteMapping(path = "{id}")
    public String deleteUserById(@PathVariable("id") UUID id) {
       return userService.deleteUserById(id);
    }
}
