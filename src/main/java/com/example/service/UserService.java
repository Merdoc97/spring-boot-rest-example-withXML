package com.example.service;

import com.example.dao.UserDao;
import com.example.model.Users;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Igor
 */
@Service
@Transactional
public class UserService {

    private final UserDao userDao;
    private final SessionFactory sessionFactory;

    @Autowired
    public UserService(UserDao userDao, SessionFactory sessionFactory) {
        this.userDao = userDao;
        this.sessionFactory = sessionFactory;
    }

    public Users getByName(String name) {
        return userDao.getByName(name);
    }

    public List<Users> getAll() {
        return userDao.getAll();
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(userDao.getById(id));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addUser(String name) {
        sessionFactory.getCurrentSession().saveOrUpdate(new Users(name));
    }

    public Users getUser(Integer id) {
        return userDao.getById(id);
    }

}
