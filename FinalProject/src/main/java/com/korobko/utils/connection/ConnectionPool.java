package com.korobko.utils.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Vova Korobko
 */
class ConnectionPool {
    private static final String JAVA_COMP_ENV = "java:comp/env";
    private static final String RESOURCE_REFERENCE_NAME = "jdbc/CarFleetDB";
    private static DataSource dataSource;
    final static Logger logger = LoggerFactory.getLogger(ConnectionPool.class);
    static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(JAVA_COMP_ENV);
            dataSource = (DataSource) envContext.lookup(RESOURCE_REFERENCE_NAME);
        } catch (NamingException e) {
           logger.error("Exception initialize data source", e);
        }
    }
    private ConnectionPool() {}

    /**
     * <p>Attempts to establish a connection with the data source that
     * this {@code DataSource} object represents.
     *
     * @return a connection to the data source
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            logger.error("Exception getting connection", e);
        }
        return connection;
    }
}
