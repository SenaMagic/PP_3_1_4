package ru.kata.spring.boot_security.demo.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Starter {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public Starter(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void starter() {
        if (userService.listUsers().isEmpty() & roleService.roleList().isEmpty()) {
            Role user = new Role("ROLE_USER");
            Role admin = new Role("ROLE_ADMIN");
            roleService.addRole(user);
            roleService.addRole(admin);

            Set<Role> rolesForUser = new HashSet<>();
            rolesForUser.add(user);
            Set<Role> rolesForAdmin = new HashSet<>();
            rolesForAdmin.add(admin);

            userService.addUser(new User("user", "user",
                    "user", "user", "user@user.user", rolesForUser));
            userService.addUser(new User("admin", "admin",
                    "admin", "admin", "admin@admin.admin", rolesForAdmin));

        }
    }
}
