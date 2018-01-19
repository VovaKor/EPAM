package com.korobko.dao;

import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * An object used to perform CRUD operations with application entities.
 *
 * @author Vova Korobko
 */
public interface Dao {
    Logger logger = LoggerFactory.getLogger(Dao.class);

    /**
     * Releases this {@code Statement} and {@code ConnectionWrapper} object's database
     * and JDBC resources immediately instead of waiting for
     * this to happen when it is automatically closed.
     * It is generally good practice to release resources as soon as
     * you are finished with them to avoid tying up database
     * resources.
     * <P>
     * Calling the method {@code closeResources} on a {@code Statement}
     * and {@code ConnectionWrapper} objects that is already closed has
     * no effect.
     *
     * @param connectionWrapper {@code ConnectionWrapper} to close
     * @param preparedStatement {@code PreparedStatement} to close
     */
    default void closeResources(ConnectionWrapper connectionWrapper, PreparedStatement preparedStatement) {
        closeResources(connectionWrapper, preparedStatement, null);
    }

    /**
     * Releases this {@code Statement}, {@code ConnectionWrapper} and
     * {@code ResultSet} object's database
     * and JDBC resources immediately instead of waiting for
     * this to happen when it is automatically closed.
     * It is generally good practice to release resources as soon as
     * you are finished with them to avoid tying up database
     * resources.
     * <P>
     * Calling the method {@code closeResources} on a {@code Statement},
     * {@code ConnectionWrapper} and {@code ResultSet} objects that is
     * already closed has no effect.
     *
     * @param connectionWrapper {@code ConnectionWrapper} to close
     * @param preparedStatement {@code PreparedStatement} to close
     * @param resultSet {@code ResultSet} to close
     */
    default void closeResources(ConnectionWrapper connectionWrapper, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (Objects.nonNull(resultSet)) {
                resultSet.close();
            }
            if (Objects.nonNull(preparedStatement)) {
                preparedStatement.close();
            }
            if (Objects.nonNull(connectionWrapper)) {
                connectionWrapper.close();
            }
        } catch (SQLException e) {
            logger.error("Exception close resources", e);
        }
    }

    /**
     * Executes the SQL statement with this <code>String</code> object,
     * which must be an SQL Data Manipulation Language (DML) statement, such as <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code> with one parameter to set.
     *
     * @param parameter the Java <code>String</code> value
     * @param sql the SQL statement
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if no row was affected or exception happened
     */
    default int executeUpdate(String parameter, String sql) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, parameter);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
    /**
     * Executes the SQL statement with this <code>Long</code> object,
     * which must be an SQL Data Manipulation Language (DML) statement, such as <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code> with one parameter to set.
     *
     * @param id the Java <code>Long</code> value
     * @param sql the SQL statement
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if no row was affected or exception happened
     */
    default int executeUpdate(Long id, String sql) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    /**
     * Executes the SQL statement with this <code>Long</code> and <code>String</code> objects,
     * which must be an SQL Data Manipulation Language (DML) statement, such as <code>INSERT</code>, <code>UPDATE</code> or
     * <code>DELETE</code> with two parameters to set.
     *
     * @param longParam the Java <code>Long</code> value
     * @param stringParam the Java <code>String</code> value
     * @param sql the SQL statement
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if no row was affected or exception happened
     */
    default int executeUpdate(Long longParam, String stringParam, String sql) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, stringParam);
            preparedStatement.setLong(2, longParam);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
}

