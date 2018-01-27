package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.*;

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
        int result = 0;
        if (InputValidator.isPositiveInteger(routeNumber)) {
            Integer number = Integer.valueOf(routeNumber);
            if (number == 0) {
                number = null;
            }
            result = BusService.INSTANCE.updateBusRoute(number, vin);
        }
        if (ROWS_AFFECTED == result) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_ROUTE_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_BUS_ROUTE));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_FEEDBACK);
    }
}
