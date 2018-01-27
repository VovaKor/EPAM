package com.korobko.commands;

import com.korobko.services.RouteService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.dao.DBColumns.*;
import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class CreateRouteCommand implements Command {

    private static final String MESSAGE_ERROR_CREATE_ROUTE = "message.error.create.route";
    private static final String MESSAGE_ROUTE_CREATED = "message.route.created";

    @Override
    public String execute(HttpServletRequest request) {
        String routeNumber = request.getParameter(ROUTE_NUMBER);
        String begin = request.getParameter(BEGIN_POINT);
        String end = request.getParameter(END_POINT);
        int result = 0;
        if (InputValidator.nonNullnotEmpty(routeNumber, begin, end)
                && InputValidator.isPositiveInteger(routeNumber)) {
            Integer rNumber = Integer.valueOf(routeNumber);
            if (rNumber > 0) {
                result = RouteService.INSTANCE.createRoute(rNumber, begin, end);
            }
        }
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ROUTE_CREATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_CREATE_ROUTE));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
