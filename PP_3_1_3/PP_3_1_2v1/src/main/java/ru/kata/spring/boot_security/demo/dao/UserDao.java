package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);
    List<User> listUsers();
    void updateUser(long id, User user);
    void deleteUser(long id);
    User getUserById(long id);
    User getUserByUsername(String userName);
}
