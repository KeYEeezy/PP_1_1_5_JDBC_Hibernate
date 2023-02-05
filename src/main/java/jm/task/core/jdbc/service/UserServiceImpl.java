package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    //UserDao userDao = new UserDaoJDBCImpl();
    UserDao userDaoHiber = new UserDaoHibernateImpl();




    public void createUsersTable() {
        userDaoHiber.createUsersTable();
    }

    public void dropUsersTable() {
       userDaoHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHiber.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHiber.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHiber.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHiber.cleanUsersTable();
    }
}
