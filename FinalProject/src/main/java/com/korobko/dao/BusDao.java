package com.korobko.dao;

import com.korobko.entities.Bus;
import com.korobko.entities.BusModel;
import com.korobko.entities.Route;
import com.korobko.utils.ResourceManager;
import com.korobko.utils.connection.ConnectionWrapper;
import com.korobko.utils.connection.TransactionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public class BusDao implements Dao {

    private static final String SELECT_BUSES = "select.buses";
    private static final String SELECT_BUS_BY_VIN = "select.bus.by.vin";
    private static final String UPDATE_BUS_ROUTE = "update.bus.route";
    private static final String SELECT_FREE_BUS_IDS = "select.free.bus.ids";
    private static final String INSERT_BUS = "insert.bus";
    private static final String DELETE_BUS = "delete.bus";
    private static final String UPDATE_BUS_PARAMS = "update.bus.params";
    private static final String UPDATE_BUS_FULL = "update.bus.full";
    private static final String SELECT_BUSES_ROW_COUNT = "select.buses.row.count";

    BusDao() {
    }

    /**
     * Gets rows amount from database {@code buses} table
     *
     * @return either (1) the Java {@code int} rows amount
     *         or (2) 0 if exception happened or no rows in table
     */
    @Override
    public int getRowCount() {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int rowCount = 0;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_BUSES_ROW_COUNT);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            logger.error("Exception while getting buses row count", e);
        } finally {
            closeResources(wrapper, statement, resultSet);
        }
        return rowCount;
    }
    /**
     * Fetches {@code Bus} objects sorted by {@code Route} number in descending
     * order limited by {@param offset} and {@param rowsPerPage}.
     *
     * @param offset the {@code int} value offset against first row in the table
     * @param rowsPerPage the {@code int} value max size of resulted {@code List}
     * @return {@code List} of {@code Bus} objects
     */
    public List<Bus> getAllBuses(int offset, int rowsPerPage) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bus> buses = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_BUSES);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setInt(1, offset);
            statement.setInt(2, rowsPerPage);
            resultSet = statement.executeQuery();
            buses = new ArrayList<>();
            while (resultSet.next()) {
                Bus bus = new Bus();
                bus.setVIN(resultSet.getString(DBColumns.VIN));
                bus.setRegistrationNumber(resultSet.getString(DBColumns.REGISTR_NUMBER));
                BusModel busModel = new BusModel();
                busModel.setModelId(resultSet.getLong(DBColumns.MODEL_ID));
                busModel.setModelName(resultSet.getString(DBColumns.MODEL_NAME));
                bus.setBusModel(busModel);
                Route route = new Route();
                route.setRouteNumber(resultSet.getInt(DBColumns.ROUTE_NUMBER));
                route.setBeginPoint(resultSet.getString(DBColumns.BEGIN_POINT));
                route.setEndPoint(resultSet.getString(DBColumns.END_POINT));
                bus.setRoute(route);
                buses.add(bus);
            }

        } catch (SQLException e) {
            logger.error("Exception while getting all buses", e);
        } finally {
            closeResources(wrapper, statement, resultSet);
        }
        return buses;
    }

    /**
     * Fetches {@code Bus} object by VIN
     *
     * @param vin the Java {@code String} value {@code Bus} VIN
     * @return either (1) {@code Bus} object
     *         or (2) {@code null} if exception happened or no VIN
     *         in database
     */
    public Bus getBusByVIN(String vin) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Bus bus = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_BUS_BY_VIN);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, vin);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bus = new Bus();
                bus.setVIN(vin);
                bus.setRegistrationNumber(resultSet.getString(DBColumns.REGISTR_NUMBER));
                BusModel busModel = new BusModel();
                busModel.setModelName(resultSet.getString(DBColumns.MODEL_NAME));
                bus.setBusModel(busModel);
                Route route = new Route();
                route.setRouteNumber(resultSet.getInt(DBColumns.ROUTE_NUMBER));
                route.setBeginPoint(resultSet.getString(DBColumns.BEGIN_POINT));
                route.setEndPoint(resultSet.getString(DBColumns.END_POINT));
                bus.setRoute(route);
            }

        } catch (SQLException e) {
            logger.error("Exception while getting bus by VIN", e);
        } finally {
            closeResources(wrapper, statement, resultSet);
        }
        return bus;
    }

    /**
     * Updates {@code Bus} in database {@code buses} table. If given {@code Integer}
     * parameter is 0 then sets column {@code route_number} to SQL {@code NULL}
     *
     * @param number the Java {@code Integer} value {@code Route} number
     * @param vin the Java {@code String} value {@code Bus} VIN
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateBusRoute(Integer number, String vin) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_BUS_ROUTE);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            if (Objects.nonNull(number)) {
                statement.setInt(1, number);
            } else {
                statement.setNull(1, Types.NULL);
            }
            statement.setString(2, vin);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception bus route renewal", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    /**
     * Fetches {@code Bus} ids where {@code Route} number is {@code null}
     *
     * @return either (1) the {@code List<String>} with ids
     *         or (2) {@code null} if exception happened
     */
    public List<String> findFreeBusIds() {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<String> ids = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_FREE_BUS_IDS);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            resultSet = statement.executeQuery();
            ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(resultSet.getString(DBColumns.VIN));
            }

        } catch (SQLException e) {
            logger.error("Exception while getting free bus ids", e);
        } finally {
            closeResources(wrapper, statement, resultSet);
        }
        return ids;
    }

    /**
     * Creates new row in database {@code buses} table
     *
     * @param vin the {@code String} value {@code Bus} VIN
     * @param registrNumber the {@code String} value {@code Bus} registration number
     * @param modelId the {@code Long} value {@code BusModel} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int insertBus(String vin, String registrNumber, Long modelId) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(INSERT_BUS);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, vin);
            statement.setString(2, registrNumber);
            statement.setLong(3, modelId);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while inserting bus", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    /**
     * Deletes row from database {@code buses} table with given parameter
     *
     * @param vin the {@code String} value {@code Bus} VIN
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int deleteBus(String vin) {
        String sql = ResourceManager.QUERIES.getProperty(DELETE_BUS);
        return executeUpdate(vin, sql);
    }

    /**
     * Updates {@code Bus} in database {@code buses} table.
     *
     * @param vin the {@code String} value {@code Bus} VIN
     * @param registrNumber the {@code String} value {@code Bus} registration number
     * @param modelId the {@code Long} value {@code BusModel} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateBusParams(String vin, String registrNumber, Long modelId) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_BUS_PARAMS);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, registrNumber);
            statement.setLong(2, modelId);
            statement.setString(3, vin);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating bus params", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    /**
     * Updates all columns in database {@code buses} table with given {@param oldVin}
     * parameter and sets {@param newVin} instead.
     *
     * @param oldVin the {@code String} value {@code Bus} VIN
     * @param newVin the {@code String} value {@code Bus} VIN to insert
     * @param registrNumber the {@code String} value {@code Bus} registration number
     * @param modelId the {@code Long} value {@code BusModel} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateFullBus(String oldVin, String newVin, String registrNumber, Long modelId) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_BUS_FULL);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, newVin);
            statement.setString(2, registrNumber);
            statement.setLong(3, modelId);
            statement.setString(4, oldVin);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating full bus", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }
}
