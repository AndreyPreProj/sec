package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
public class AdministrationServiceImpl implements AdministrationService{

    private final UserDao userDao;
    private final RoleDao roleDao;


    public AdministrationServiceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void save(User user) {
        Optional<Role> role = roleDao.findById(1);
        if (role.isPresent()) {
            user.addRole(role.get());
            userDao.save(user);
        }
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

  @Transactional
    @Override
    public void update(User user) {
        userDao.merge(user.getName(), user.getSurname(), user.getUserId());
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(userDao.getById(id));
    }
}
