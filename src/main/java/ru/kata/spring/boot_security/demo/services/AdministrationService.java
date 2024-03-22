package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import java.util.List;


public interface AdministrationService {
    public List<User> getAll();
    public void save(User user);
    public <Optional>User getById(int id);
    public void update(User user);
    public void delete(int id);
}
