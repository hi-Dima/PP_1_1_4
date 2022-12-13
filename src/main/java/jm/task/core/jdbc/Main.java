package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl y = new UserDaoJDBCImpl();
        y.createUsersTable();
//        y.saveUser("Римgggа", "Иванов", (byte) 27);
//        y.saveUser("Римgggа", "Ивgdgdfанов", (byte) 27);
//            y.cleanUsersTable();
//        y.createUsersTable();
        y.saveUser("Коля1", "Ивgdgdfанов", (byte) 27);
        y.saveUser("Коля2", "Ивgdgdfанов", (byte) 27);
        y.saveUser("Коля3", "Ивgdgdfанов", (byte) 27);
        y.removeUserById(2);
        System.out.println(y.getAllUsers());
//        System.out.println(y.getAllUsers());
//        y.saveUser("Толя", "Ивgdgdfанов", (byte) 27);
//        System.out.println(y.getAllUsers());
//        y.removeUserById(2);
//        System.out.println(y.getAllUsers());
//        y.saveUser("Настя", "Иванов", (byte) 27);
//        y.saveUser("Света", "Ивgdgdfанов", (byte) 27);

//        y.removeUserById(3);

    }
}
