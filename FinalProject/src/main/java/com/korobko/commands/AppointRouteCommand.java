package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Bus;
import com.korobko.entities.Route;
import com.korobko.services.BusService;
import com.korobko.services.RouteService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class AppointRouteCommand implements Command {

    private static final String ATTR_NAME_BUS = "bus";
    private static final String ATTR_NAME_ROUTES = "routes";
    private static final String PATH_PAGE_APPOINT_ROUTE = "path.page.appoint_route";

    @Override
    public String execute(HttpServletRequest request) {
        String vin = request.getParameter(DBColumns.VIN);
        Bus bus = BusService.INSTANCE.getBusByVIN(vin);
        List<Route> routes = RouteService.INSTANCE.getAllRoutes();
        request.setAttribute(ATTR_NAME_BUS, bus);
        request.setAttribute(ATTR_NAME_ROUTES, routes);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_APPOINT_ROUTE);
    }
}
