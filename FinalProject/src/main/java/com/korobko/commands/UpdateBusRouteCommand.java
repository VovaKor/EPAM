package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.ATTR_NAME_FEEDBACK_MESSAGE;
import static com.korobko.utils.Constants.PATH_PAGE_ADMIN_FEEDBACK;
import static com.korobko.utils.Constants.ROWS_AFFECTED;

/**
 * @author Vova Korobko
 */
public class UpdateBusRouteCommand implements Command {

    private static final String MESSAGE_BUS_ROUTE_UPDATED = "message.bus.route.updated";
    private static final String MESSAGE_ERROR_UPDATE_BUS_ROUTE = "message.error.update.bus.route";

    @Override
    public String execute(HttpServletRequest request) {
        String routeNumber = request.getParameter(DBColumns.ROUTE_NUMBER);
        String vin = request.getParameter(DBColumns.VIN);
        int result = BusService.INSTANCE.updateBusRoute(routeNumber, vin);
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_ROUTE_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_BUS_ROUTE));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_FEEDBACK);
    }
}
