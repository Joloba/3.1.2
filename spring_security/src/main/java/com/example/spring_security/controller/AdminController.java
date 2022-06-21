package com.example.spring_security.controller;

import com.example.spring_security.model.Role;
import com.example.spring_security.service.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;
    private final PasswordEncoder encoder;

    public AdminController(UserService userService, RoleService roleService, PasswordEncoder encoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.encoder = encoder;
    }

    @GetMapping("/users")
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "index";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user, @RequestParam(required = false, value = "nameRoles") String[] roles) {
        List<Role> roles1 = new ArrayList<>();
        for (String role : roles) {
            roles1.add(roleService.getRoleByName(role));
        }
        user.setRolesList(roles1);
        user.setPassword(encoder.encode(user.getPassword()));
        userService.saveUser(user);
//        user.setPassword(encoder.encode(user.getPassword()));
//        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/{id}")
    public String showUsersById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "new";
    }

    @GetMapping("/users/{id}/edit")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit";
    }

    @PatchMapping("/users/{id}")
    public String editUser(@ModelAttribute("user") User user, @RequestParam (value = "editRole") String[] roles1) {
        List<Role> roles = new ArrayList<>();
        for(String role:roles1){
            roles.add(roleService.getRoleByName(role));
        }
        user.setRolesList(roles);
        userService.editUser(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
