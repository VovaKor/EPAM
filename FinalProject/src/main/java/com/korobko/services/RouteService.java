package com.korobko.services;

import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.dao.RouteDao;
import com.korobko.entities.Route;
import com.korobko.utils.Constants;
import com.korobko.utils.InputValidator;

import java.util.List;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum RouteService {
    INSTANCE;
    private RouteDao routeDao;
    RouteService() {
        this.routeDao = (RouteDao) DaoFactory.INSTANCE.getDao(DaoType.ROUTE);
    }

    public List<Route> getAllRoutes() {
        return routeDao.getRoutes();
    }

    public int createRoute(Integer routeNumber, String begin, String end) {
        return routeDao.insertRoute(routeNumber, begin, end);
    }

    public int processRoute(Integer oldRouteNumber, Integer newRouteNumber, String begin, String end) {

        if (newRouteNumber == 0) {
            return routeDao.deleteRoute(oldRouteNumber);
        }
        if (oldRouteNumber.intValue() == newRouteNumber.intValue()) {
            return routeDao.updateRoutePoints(oldRouteNumber, begin, end);
        }
        return routeDao.updateFullRoute(oldRouteNumber, newRouteNumber, begin, end);
    }
}
