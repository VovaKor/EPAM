package com.korobko.utils.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
class ConnectionPool {
    private static final String JAVA_COMP_ENV = "java:comp/env";
    private static final String RESOURCE_REFERENCE_NAME = "jdbc/CarFleetDB";
    private static DataSource dataSource;
    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(JAVA_COMP_ENV);
            dataSource = (DataSource) envContext.lookup(RESOURCE_REFERENCE_NAME);
        } catch (NamingException e) {
           //TODO
        }
    }
    private ConnectionPool() {}

    /**
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            //todo
        }
        return connection;
    }
}
