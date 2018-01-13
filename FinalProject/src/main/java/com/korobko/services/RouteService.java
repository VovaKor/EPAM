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

    public int createRoute(String routeNumber, String begin, String end) {
        if (InputValidator.isNullOrEmpty(routeNumber, begin, end)) {
            return Constants.ERROR_CODE;
        }
        if (InputValidator.nonInteger(routeNumber)) {
            return Constants.ERROR_CODE;
        }
        Integer rNumber = Integer.valueOf(routeNumber);
        if (rNumber == 0) {
            return Constants.ERROR_CODE;
        }
        return routeDao.insertRoute(rNumber, begin, end);
    }

    public int processRoute(String oldRouteNumber, String newRouteNumber, String begin, String end) {
        if (InputValidator.isNullOrEmpty(oldRouteNumber, newRouteNumber, begin, end)) {
            return Constants.ERROR_CODE;
        }
        if (InputValidator.nonInteger(oldRouteNumber) || InputValidator.nonInteger(newRouteNumber)) {
            return Constants.ERROR_CODE;
        }
        Integer oldRNumber = Integer.valueOf(oldRouteNumber);
        Integer newRNumber = Integer.valueOf(newRouteNumber);
        if (newRNumber == 0) {
            return routeDao.deleteRoute(oldRNumber);
        }
        if (oldRNumber.intValue() == newRNumber.intValue()) {
            return routeDao.updateRoutePoints(oldRNumber, begin, end);
        }
        return routeDao.updateFullRoute(oldRNumber, newRNumber, begin, end);
    }
}
