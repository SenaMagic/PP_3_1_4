package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MainController {

    private final UserService userService;
    private final RoleService roleService;
    @Autowired
    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/user")
    public String userPage(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
    @GetMapping(value = "/admin")
    public String userList(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("result", userService.listUsers());
        model.addAttribute("roles", roleService.roleList());
        return "adminpanel";
    }
    @PostMapping("/admin/adduser")
    public String addUser(@ModelAttribute("user") /*@Valid*/ User user) {
        if (user.getRoles() == null) {
            Set<Role> baseRole = new HashSet<>();
            baseRole.add(roleService.getRoleById((long) 1));
            user.setRoles(baseRole);
        }
        userService.addUser(user);
        return "redirect:/admin";
    }
    @PutMapping("/admin/{id}/edited")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(id, user);
        return "redirect:/admin";
    }
    @DeleteMapping("/admin/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
