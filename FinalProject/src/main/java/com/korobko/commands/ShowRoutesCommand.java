package com.korobko.commands;

import com.korobko.entities.Route;
import com.korobko.services.RouteService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class ShowRoutesCommand implements Command {

    private static final String ATTR_NAME_ROUTE_LIST = "route_list";
    private static final String PATH_PAGE_DIRECTOR_ROUTES = "path.page.director.routes";

    @Override
    public String execute(HttpServletRequest request) {
        List<Route> routes = RouteService.INSTANCE.getAllRoutes();
        request.setAttribute(ATTR_NAME_ROUTE_LIST, routes);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_ROUTES);
    }
}
