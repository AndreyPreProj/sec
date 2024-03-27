package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdministrationService;
import ru.kata.spring.boot_security.demo.services.AdministrationServiceImpl;

@RestController
@RequestMapping("/api")
public class UserResource {
    private final AdministrationServiceImpl administrationService;

    @Autowired
    public UserResource(AdministrationServiceImpl administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping("/api/role/{id}")
    public Role getRole(@PathVariable String id) {
        return administrationService.getRole(id);
    }

    @GetMapping("/update/{id}")
    public User getUserById(@PathVariable int id) {

        return administrationService.getById(id);
    }

    @PostMapping("/update/{id}")
    public void update(@RequestBody User user, @PathVariable String id) {
        String[] roles = user.getBetween().replace(" ", "").split(",");

        if (roles.length >= 2) {
            for (String role : roles) {
                user.addRole(administrationService.getRole(role));
            }


            administrationService.update(user);
            return;
        }

        if (roles.length == 0) {
            //
        } else {
            user.addRole(administrationService.getRole(user.getBetween()));

            if (!user.getRoles().contains(administrationService.getRole(id))) {
                user.addRole(administrationService.getRole(id));
            }
        }

        administrationService.update(user);
    }



}
