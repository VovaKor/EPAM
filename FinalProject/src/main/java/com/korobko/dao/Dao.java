package com.korobko.dao;

import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public interface Dao {
    default void closeResources(ConnectionWrapper connectionWrapper, PreparedStatement preparedStatement) {
        try {
            if (Objects.nonNull(preparedStatement)) {
                preparedStatement.close();
            }
            if (Objects.nonNull(connectionWrapper)) {
                connectionWrapper.close();
            }
        } catch (SQLException e) {
            //todo
        }
    }

    /**
     *
     * @param parameter
     * @param sql
     * @return
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
            //todo
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
    /**
     * todo
     * @param id
     * @param sql
     * @return
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
            //todo
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }

    /**
     * todo
     * @param id
     * @param parameter
     * @param sql
     * @return
     */
    default int executeUpdate(Long id, String parameter, String sql) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, parameter);
            preparedStatement.setLong(2, id);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            //todo
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
}

