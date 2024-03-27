package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.AdministrationServiceImpl;



@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdministrationServiceImpl administrationService;

    @Autowired
    public AdminController(AdministrationServiceImpl administrationService) {
        this.administrationService = administrationService;
    }

    @GetMapping
    public ModelAndView adminPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        model.addAttribute("me", user);
        model.addAttribute("all", administrationService.getAll());
        model.addAttribute("roles", administrationService.getAllRoles());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("model", model);

        return modelAndView;
    }

    @GetMapping("/update/{id}")
    public String getUserById(@PathVariable int id, Model model) {
        model.addAttribute("update", administrationService.getById(id));
        return "update";
    }

    @PostMapping("/update")
    public ModelAndView  update(User user, Model model) {
        administrationService.update(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("model", model);

        return modelAndView;
    }


    @GetMapping("/creation")
    public ModelAndView registration(@ModelAttribute("user") User user, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User me = (User) auth.getPrincipal();
        model.addAttribute("me", me);
        model.addAttribute("roles", administrationService.getAllRoles());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("creation");
        modelAndView.addObject("model", model);

        return modelAndView;
    }

    @PostMapping("/creation")
    public ModelAndView performRegistration(@ModelAttribute("user") User user, Model model) {
        administrationService.save(user);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        modelAndView.addObject("model", model);

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") int id, Model model) {
        administrationService.delete(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("model", model);

        return modelAndView;
    }
}