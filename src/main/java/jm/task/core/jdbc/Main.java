package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Bakin", (byte)23);
        userService.saveUser("Sasha", "Petrov", (byte)25);
        userService.saveUser("Petya", "Alexandrov", (byte)20);
        userService.saveUser("Alexandr", "Nevsky", (byte)33);
        List<User> list = userService.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
        userService.removeUserById(3);
        List<User> listBeforeDelete = userService.getAllUsers();
        for (User user : listBeforeDelete) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
