package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/update/{id}")
    public User getUserById(@PathVariable int id) {
        return administrationService.getById(id);
    }

    @PostMapping("/update")
    public void update(@RequestBody User user) {

        administrationService.update(user);
    }



}
