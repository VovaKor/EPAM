package com.korobko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Vova Korobko
 */
public class Factory {

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/FILE_SYSTEM",
                    "root",
                    "rootroot");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
