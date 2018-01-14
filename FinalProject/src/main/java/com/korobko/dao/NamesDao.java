package com.korobko.dao;

import com.korobko.entities.Names;
import com.korobko.utils.ResourceManager;
import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.korobko.dao.DBColumns.*;

/**
 * @author Vova Korobko
 */
public class NamesDao implements Dao {
    private static final String SELECT_NAMES_BY_ID = "select.names.by.id";
    private static final String INSERT_NAMES = "insert.names";

    NamesDao() {
    }

    public Names findNamesByEmployeeId(Long employeeId) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        Names names = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_NAMES_BY_ID);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setLong(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                names = new Names();
                names.setFirstName(resultSet.getString(FIRST_NAME));
                names.setLastName(resultSet.getString(LAST_NAME));
                names.setPatronymic(resultSet.getString(PATRONYMIC));
            }
        } catch (SQLException e) {
            logger.error("Exception finding names by employee id", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return names;
    }

    public int insertNames(String firstName, String patronymic, String lastName) {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(INSERT_NAMES);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, patronymic);
            preparedStatement.setString(3, lastName);
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while inserting employee names", e);
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return result;
    }
}
