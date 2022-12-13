package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    static String request;
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        boolean execute;
        request = """
                 CREATE TABLE  if not exists UTable  (
                 id int AUTO_INCREMENT,
                 name varchar (45) NOT NULL,
                 last_name varchar (45) NOT NULL,
                 age int NOT NULL,
                 PRIMARY KEY (id)
                 );""";
        Util.connectionAndStatement(request);
    }

    public void dropUsersTable() {
        request = "DROP TABLE  if exists UTable";
        Util.connectionAndStatement(request);
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setAge(age);
        user.setName(name);
        user.setLastName(lastName);
//        list.add(user);
        String sql = "INSERT INTO UTable (name, last_name, age) Values (?, ?, ?)";
        try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
                prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getLastName());
            ps.setByte(3, user.getAge());
            boolean y = ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
//        String sql1 = "SELECT id FROM UTable ORDER BY id DESC LIMIT 1";
//        try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
//                prepareStatement(sql1)) {
//            ResultSet y = ps.executeQuery();
//            while (y.next()) {
//                user.setId((long) y.getInt(1));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }

        public void removeUserById(long id) {
            String sql = """
                    DELETE FROM UTable
                    WHERE id = ?
                    """;
            try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
                    prepareStatement(sql)) {
                ps.setLong(1, id);

                boolean y = ps.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
                prepareStatement("SELECT * FROM UTable")) {
            ResultSet y = ps.executeQuery();
            while (y.next()) {
                users.add(new User(y.getString(2),y.getString(3),y.getByte(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users ;
    }

    public void cleanUsersTable() {
        request = "TRUNCATE TABLE UTable";
        Util.connectionAndStatement(request);
    }
}
