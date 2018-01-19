package com.korobko.dao;

import com.korobko.entities.Route;
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
public class RouteDao implements Dao {

    private static final String SELECT_ROUTES = "select.routes";
    private static final String INSERT_ROUTE = "insert.route";
    private static final String DELETE_ROUTE = "delete.route";
    private static final String UPDATE_ROUTE_POINTS = "update.route.points";
    private static final String UPDATE_ROUTE_FULL = "update.route.full";

    RouteDao() {
    }

    public List<Route> getRoutes() {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Route> routes = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_ROUTES);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            resultSet = statement.executeQuery();
            routes = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route();
                route.setRouteNumber(resultSet.getInt(DBColumns.ROUTE_NUMBER));
                route.setBeginPoint(resultSet.getString(DBColumns.BEGIN_POINT));
                route.setEndPoint(resultSet.getString(DBColumns.END_POINT));
                routes.add(route);
            }

        } catch (SQLException e) {
            logger.error("Exception while getting routes", e);
        } finally {
            closeResources(wrapper, statement, resultSet);
        }
        return routes;
    }

    public int insertRoute(Integer rNumber, String begin, String end) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(INSERT_ROUTE);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setInt(1, rNumber);
            statement.setString(2, begin);
            statement.setString(3, end);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while inserting route", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    public int deleteRoute(Integer newRNumber) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(DELETE_ROUTE);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setInt(1, newRNumber);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while deleting route", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    public int updateRoutePoints(Integer routeNumber, String begin, String end) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_ROUTE_POINTS);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, begin);
            statement.setString(2, end);
            statement.setInt(3, routeNumber);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating route points", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    /**
     * Updates all columns in database {@code routes} table with given {@param oldRNumber}
     * parameter and sets {@param newRNumber} instead.
     *
     * @param oldRNumber the {@code Integer} value {@code Route} number
     * @param newRNumber the {@code Integer} value {@code Route} number to insert
     * @param begin the {@code String} value {@code Route} begin point
     * @param end the {@code String} value {@code Route} end point
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int updateFullRoute(Integer oldRNumber, Integer newRNumber, String begin, String end) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_ROUTE_FULL);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setInt(1, newRNumber);
            statement.setString(2, begin);
            statement.setString(3, end);
            statement.setInt(4, oldRNumber);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Exception while updating full route", e);
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }
}
