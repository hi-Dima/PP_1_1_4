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
        Util.connectionAndStatement("""
                 CREATE TABLE  if not exists UTable  (
                 id int AUTO_INCREMENT,
                 name varchar (45) NOT NULL,
                 last_name varchar (45) NOT NULL,
                 age int NOT NULL,
                 PRIMARY KEY (id)
                 );""");
    }

    public void dropUsersTable() {
        Util.connectionAndStatement("DROP TABLE  if exists UTable");
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
                prepareStatement("INSERT INTO UTable (name, last_name, age) Values (?, ?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            boolean y = ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void removeUserById(long id) {
            try (PreparedStatement ps = DriverManager.getConnection(Util.connectionURL, Util.userName, Util.password).
                    prepareStatement("""
                    DELETE FROM UTable
                    WHERE id = ?
                    """)) {
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
        Util.connectionAndStatement("TRUNCATE TABLE UTable");
    }
}
