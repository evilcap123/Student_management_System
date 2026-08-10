package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection{

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/?user=evilcap";
    private static final String USER = "evilcap";
    private static final String PASSWORD = "Mehugyan12";

    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL , USER , PASSWORD);
    }
}