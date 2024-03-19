package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdministrationServiceImpl;



@RequestMapping("/admin")
@Controller
public class AdminController {

    private final AdministrationServiceImpl administrationService;

    @Autowired
    public AdminController(AdministrationServiceImpl administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }


    @GetMapping("/creation")
    public String registration(@ModelAttribute("user") User user) {
        return "creation";
    }

    @PostMapping("/creation")
    public String performRegistration(@ModelAttribute("user") User user) {
        administrationService.save(user);

        return "redirect:/login";
    }

    @GetMapping("/update")
    public String getPersonById(@RequestParam("id") int id, Model model) {
        model.addAttribute("updatedPerson", administrationService.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("updatedPerson") User user) {
        administrationService.update(user);
        return "redirect:/login";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        administrationService.delete(id);
        return "redirect:/admin";
    }
}