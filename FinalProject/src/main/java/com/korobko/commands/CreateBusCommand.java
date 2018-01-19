package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.ATTR_NAME_FEEDBACK_MESSAGE;
import static com.korobko.utils.Constants.PATH_PAGE_DIRECTOR_FEEDBACK;
import static com.korobko.utils.Constants.ROWS_AFFECTED;

/**
 * @author Vova Korobko
 */
public class CreateBusCommand implements Command {

    private static final String MESSAGE_ERROR_CREATE_BUS = "message.error.create.bus";
    private static final String MESSAGE_BUS_CREATED = "message.bus.created";

    @Override
    public String execute(HttpServletRequest request) {
        String vin = request.getParameter(DBColumns.VIN);
        String registrNumber = request.getParameter(DBColumns.REGISTR_NUMBER);
        String modelId = request.getParameter(DBColumns.MODEL_ID);
        int result = 0;
        if (InputValidator.nonNullnotEmpty(vin, modelId)) {
            result = BusService.INSTANCE.createBus(vin, registrNumber, modelId);
        }
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_CREATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_CREATE_BUS));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);

    }
}
