package com.korobko.commands;

import com.korobko.services.RouteService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.dao.DBColumns.BEGIN_POINT;
import static com.korobko.dao.DBColumns.END_POINT;
import static com.korobko.dao.DBColumns.ROUTE_NUMBER;
import static com.korobko.utils.Constants.ATTR_NAME_FEEDBACK_MESSAGE;
import static com.korobko.utils.Constants.PATH_PAGE_DIRECTOR_FEEDBACK;
import static com.korobko.utils.Constants.ROWS_AFFECTED;

/**
 * @author Vova Korobko
 */
public class UpdateRouteCommand implements Command {

    private static final String PAR_NAME_OLD_ROUTE_NUMBER = "old_route_number";
    private static final String MESSAGE_ERROR_UPDATE_ROUTE = "message.error.update.route";
    private static final String MESSAGE_ROUTE_UPDATED = "message.route.updated";

    @Override
    public String execute(HttpServletRequest request) {
        String newRouteNumber = request.getParameter(ROUTE_NUMBER);
        String oldRouteNumber = request.getParameter(PAR_NAME_OLD_ROUTE_NUMBER);
        String begin = request.getParameter(BEGIN_POINT);
        String end = request.getParameter(END_POINT);
        int result = 0;
        if (InputValidator.nonNullnotEmpty(oldRouteNumber, newRouteNumber, begin, end)
                && InputValidator.isPositiveInteger(oldRouteNumber)
                && InputValidator.isPositiveInteger(newRouteNumber)) {
            Integer oldRNumber = Integer.valueOf(oldRouteNumber);
            Integer newRNumber = Integer.valueOf(newRouteNumber);
            result = RouteService.INSTANCE.processRoute(oldRNumber, newRNumber, begin, end);
        }
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ROUTE_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_ROUTE));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
