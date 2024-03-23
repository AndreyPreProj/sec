package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
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

    /*@GetMapping("/getOne")
    public User getOne(@RequestParam("id") int id) {
        System.out.println(administrationService.getById(id));
        return administrationService.getById(id);
    }*/


    @GetMapping
    public String adminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("me", user);
        model.addAttribute("all", administrationService.getAll());
        model.addAttribute("roles", administrationService.getAllRoles());
        return "admin";
    }

    @GetMapping("/update/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("update", administrationService.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public String update(User user) {
        administrationService.update(user);
        return "redirect:/admin";
    }


    @GetMapping("/creation")
    public String registration(@ModelAttribute("user") User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User me = (User) auth.getPrincipal();
        model.addAttribute("me", me);
        model.addAttribute("roles", administrationService.getAllRoles());
        return "creation";
    }

    @PostMapping("/creation")
    public String performRegistration(@ModelAttribute("user") User user) {
        administrationService.save(user);

        return "redirect:/admin";
    }

   /*@RequestMapping(value="/update", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(User user) {
        administrationService.update(user);
        return "redirect:/admin";
    }*/



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        administrationService.delete(id);
        return "redirect:/admin";
    }
}