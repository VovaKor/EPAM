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
 * @author Vova Korobko
 */
public interface Dao {
    Logger logger = LoggerFactory.getLogger(Dao.class);
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
     * todo
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
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, null);
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
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, null);
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
            logger.error("Exception execute sql", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement, null);
        }
        return result;
    }
}

