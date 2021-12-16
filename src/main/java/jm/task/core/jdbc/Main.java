package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Alex", "Bakin", (byte)23);
        userDao.saveUser("Sasha", "Petrov", (byte)25);
        userDao.saveUser("Petya", "Alexandrov", (byte)20);
        userDao.saveUser("Alexandr", "Nevsky", (byte)33);
        List<User> list = userDao.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userDao.removeUserById(3);
        List<User> listBeforeDelete = userDao.getAllUsers();
        for (User user : listBeforeDelete) {
            System.out.println(user);
        }
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
