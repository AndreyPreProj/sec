package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class AdministrationServiceImpl implements AdministrationService{

    @PersistenceContext
    private EntityManager entityManager;
    private final UserDao userDao;
    private final RoleDao roleDao;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AdministrationServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Transactional
    @Override
    public void save(User user) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.save(user);
    }

    public Role getRole(String id) {
        return roleDao.findRoleByName(id).get();
    }

    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

  @Transactional
    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);

    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(userDao.getById(id));
    }
}
