package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {}

    public void createUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(50) NOT NULL, " +
                    "lastname VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL, " +
                    "PRIMARY KEY (id))");
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            System.out.println("Таблица не создана или уже существует.");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS `db_kata`.`users`;");
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            System.out.println("Таблица не удалена или такой таблицы не существует.");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastname, age) VALUES ('" + name + "', '" + lastName + "', '" + age + "');";
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("User с именем - " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            System.out.println("Пользователь не добавлен в таблицу.");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users WHERE id = " + id);
            System.out.println("Пользователь по id = " + id + " удален из таблицы.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении пользователя или пользователя с таким id не существует.");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT * FROM users");
            while (result.next()) {
                User user = new User();
                user.setId(result.getLong("id"));
                user.setName(result.getString("name"));
                user.setLastName(result.getString("lastname"));
                user.setAge(result.getByte("age"));
                list.add(user);
            }
        } catch (SQLException ignored) {}
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE `db_kata`.`users`;");
            System.out.println("Таблица успешно очищена.");
        } catch (SQLException e) {
            System.out.println("Таблица не очищена.");
        }
    }
}
