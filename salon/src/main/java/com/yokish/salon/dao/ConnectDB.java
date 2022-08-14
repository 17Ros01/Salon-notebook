package com.yokish.salon.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/saloncourse", "root", "root");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
