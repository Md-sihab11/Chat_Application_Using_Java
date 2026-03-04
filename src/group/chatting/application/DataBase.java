package group.chatting.application;

import java.sql.*;

public class DataBase {
    private static final String URL = "jdbc:mysql://localhost:3306/chatdb";
    private static final String USER = "root";
    private static final String PASSWORD = "shakib8000";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


