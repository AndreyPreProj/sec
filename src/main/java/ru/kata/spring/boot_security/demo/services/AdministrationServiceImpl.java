package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleDao;
import ru.kata.spring.boot_security.demo.repositories.UserDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;
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

            userDao.save(user);
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

      /*User oldUser = userDao.findById(user.getUserId()).get();

      Collection<Role> roles  = oldUser.getRole();
      Role role = roleDao.findRoleByName(user.getRol()).get();

      if (roles.contains(role)) {
          // do nothing
      } else {
          roles.add(role);
      }

      user.setRole(roles);*/

        userDao.merge(user, user.getUserId());
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(userDao.getById(id));
    }
}
