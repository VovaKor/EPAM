package com.korobko.dao;

import com.korobko.entities.BusModel;
import com.korobko.utils.ResourceManager;
import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class BusModelDao implements Dao {

    private static final String SELECT_BUS_MODELS = "select.bus.models";
    private static final String INSERT_BUS_MODEL = "insert.bus.model";
    private static final String DELETE_BUS_MODEL = "delete.bus.model";
    private static final String UPDATE_BUS_MODEL = "update.bus.model";

    BusModelDao() {
    }

    public List<BusModel> getBusModels() {
        ConnectionWrapper connectionWrapper = null;
        PreparedStatement preparedStatement = null;
        List<BusModel> models = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_BUS_MODELS);
        try {
            connectionWrapper = TransactionManager.getConnectionWrapper();
            preparedStatement = connectionWrapper.getPreparedStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            models = new ArrayList<>();
            while (resultSet.next()) {
                BusModel busModel = new BusModel();
                busModel.setModelId(resultSet.getLong(DBColumns.MODEL_ID));
                busModel.setModelName(resultSet.getString(DBColumns.MODEL_NAME));
                models.add(busModel);
            }
        } catch (SQLException e) {
            //todo
        } finally {
            closeResources(connectionWrapper, preparedStatement);
        }
        return models;
    }

    public int insertBusModel(String modelName) {
        String sql = ResourceManager.QUERIES.getProperty(INSERT_BUS_MODEL);
        return executeUpdate(modelName, sql);
    }

    public int deleteBusModel(Long mId) {
        String sql = ResourceManager.QUERIES.getProperty(DELETE_BUS_MODEL);
        return executeUpdate(mId, sql);
    }

    public int updateBusModel(Long mId, String modelName) {
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_BUS_MODEL);
        return executeUpdate(mId, modelName, sql);
    }
}
