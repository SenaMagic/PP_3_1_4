package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }
    @Override
    public void updateUser(long id, User user) {
        User update = entityManager.find(User.class, id);
        update.setUsername(user.getUsername());
        update.setPassword(user.getPassword());
        update.setName(user.getName());
        update.setLastname(user.getLastname());
        update.setEmail(user.getEmail());
        entityManager.merge(update);
    }
    @Override
    public void deleteUser(long id) {
        User delete = entityManager.find(User.class, id);
        entityManager.remove(delete);
    }
    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User getUserByUsername(String userName) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", userName).getSingleResult();
    }
}
