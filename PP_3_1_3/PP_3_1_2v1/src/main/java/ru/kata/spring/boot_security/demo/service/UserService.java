package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);
    List<User> listUsers();
    void updateUser(long id, User user);
    void deleteUser(long id);
    User getUserById(long id);
    User getUserByUsername(String userName);
}
