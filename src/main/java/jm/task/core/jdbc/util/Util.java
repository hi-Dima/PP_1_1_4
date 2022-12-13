package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

        public final static String userName = "root";
        public final static String password = "1";
        public final static String connectionURL = "jdbc:mysql://localhost:3306/world";
        public static void connectionAndStatement(String request){
                try (PreparedStatement ps = DriverManager.getConnection(connectionURL, userName, password).
                        prepareStatement(request)) {
                        boolean y = ps.execute();
                } catch (SQLException e) {
                        throw new RuntimeException(e);
                }
        }



}


