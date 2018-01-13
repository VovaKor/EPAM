package com.korobko.utils.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Vova Korobko
 */
public class ConnectionWrapper {
    private Connection connection;
    private boolean isTransaction;

    public ConnectionWrapper(Connection connection, boolean isTransaction) {
        this.connection = connection;
        this.isTransaction = isTransaction;
    }

    public void close() throws SQLException {
        if (!isTransaction) {
            connection.close();
        }
    }

    Connection getConnection() {
        return connection;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}
