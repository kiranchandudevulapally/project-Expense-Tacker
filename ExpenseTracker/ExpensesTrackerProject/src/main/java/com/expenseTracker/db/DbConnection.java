package com.expenseTracker.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DbConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/expense_tracker";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "Shiva@123";

    public static Connection getConnection() throws SQLException {

        Connection con = DriverManager.getConnection(
                URL, USERNAME, PASSWORD);

        return con;
    }
}



