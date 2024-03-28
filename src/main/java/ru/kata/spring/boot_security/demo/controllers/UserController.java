package ru.kata.spring.boot_security.demo.controllers;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ModelAndView showUser(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        model.addAttribute("me", user);
        modelAndView.setViewName("user");
        modelAndView.addObject("model", model);

        return modelAndView;
    }
}