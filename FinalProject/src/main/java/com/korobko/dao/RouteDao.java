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
        List<Route> routes = null;
        String sql = ResourceManager.QUERIES.getProperty(SELECT_ROUTES);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            routes = new ArrayList<>();
            while (resultSet.next()) {
                Route route = new Route();
                route.setRouteNumber(resultSet.getInt(DBColumns.ROUTE_NUMBER));
                route.setBeginPoint(resultSet.getString(DBColumns.BEGIN_POINT));
                route.setEndPoint(resultSet.getString(DBColumns.END_POINT));
                routes.add(route);
            }

        } catch (SQLException e) {
            //todo
        } finally {
            closeResources(wrapper, statement);
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
            //todo
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
            //todo
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

    public int updateRoutePoints(Integer oldRNumber, String begin, String end) {
        ConnectionWrapper wrapper = null;
        PreparedStatement statement = null;
        int result = 0;
        String sql = ResourceManager.QUERIES.getProperty(UPDATE_ROUTE_POINTS);
        try {
            wrapper = TransactionManager.getConnectionWrapper();
            statement = wrapper.getPreparedStatement(sql);
            statement.setString(1, begin);
            statement.setString(2, end);
            statement.setInt(3, oldRNumber);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            //todo
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }

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
            //todo
        } finally {
            closeResources(wrapper, statement);
        }
        return result;
    }
}
