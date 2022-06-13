package com.example.spring_security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import com.example.spring_security.model.Role;
import com.example.spring_security.model.User;
import com.example.spring_security.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    private UserService userService;

    @Autowired
    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        List<Role> adminList = new ArrayList<>();
        adminList.add(admin);

        List<Role> userList = new ArrayList<>();
        userList.add(user);

        User sergey = new User("admin", "admin", "Sergey", "Polunin", (byte) 27, "sergpolunin95@gmail.com", adminList);
        User ivan = new User("user", "user", "Ivan", "Petrov", (byte) 43, "ivanpetrov@gmail.com", userList);

        userService.saveUser(sergey);
        userService.saveUser(ivan);

    }
}
