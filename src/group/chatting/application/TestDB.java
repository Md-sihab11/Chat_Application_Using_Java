package group.chatting.application;

import java.sql.Connection;

public class TestDB {
    public static void main(String[] args) {
        Connection conn = DataBase.getConnection();
        if (conn != null) {
            System.out.println(" Database connected successfully!");
        } else {
            System.out.println(" Failed to connect to database.");
        }
    }
}