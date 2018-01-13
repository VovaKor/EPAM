package com.korobko.utils.connection;

import com.korobko.exceptions.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public class TransactionManager {
    private static ThreadLocal<ConnectionWrapper> threadLocal = new ThreadLocal<>();
    private TransactionManager() {
    }

    public static void beginTransaction() throws TransactionException, SQLException {
        if (Objects.nonNull(threadLocal.get()))
            throw new TransactionException("Couldn't begin new transaction. Previous transaction wasn't closed.");
        Connection connection = ConnectionPool.getConnection();
        connection.setAutoCommit(false);
        ConnectionWrapper wrapper = new ConnectionWrapper(connection,true);
        threadLocal.set(wrapper);
    }

    public static void endTransaction() throws SQLException, TransactionException {
        Connection connection = getTransactionConnection("Couldn't end transaction. There was no transaction to close.");
        connection.commit();
        connection.close();
        threadLocal.set(null);
    }

    public static void rollbackTransaction() throws TransactionException, SQLException {
        Connection connection = getTransactionConnection("Couldn't rollback transaction. There was no transaction to rollback.");
        connection.rollback();
        connection.close();
        threadLocal.set(null);
    }

    private static Connection getTransactionConnection(String exceptionMessage) throws TransactionException {
        if (Objects.isNull(threadLocal.get()))
            throw new TransactionException(exceptionMessage);
        return threadLocal.get().getConnection();
    }

    public static ConnectionWrapper getConnectionWrapper() throws SQLException {
        if (Objects.isNull(threadLocal.get())){
            Connection connection = ConnectionPool.getConnection();
            return new ConnectionWrapper(connection,false);
        } else {
            return threadLocal.get();
        }
    }
}
