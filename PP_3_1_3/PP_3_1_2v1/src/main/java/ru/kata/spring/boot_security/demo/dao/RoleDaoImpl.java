package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Role> roleList() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }
    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }
}
