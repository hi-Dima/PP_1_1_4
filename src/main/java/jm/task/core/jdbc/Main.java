package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl y = new UserDaoJDBCImpl();
        y.createUsersTable();
        y.saveUser("Коля1", "Иванов", (byte) 27);
        y.saveUser("Коля2", "Иванов", (byte) 27);
        y.saveUser("Коля3", "Иванов", (byte) 27);
        y.removeUserById(2);
        System.out.println(y.getAllUsers());
    }
}
