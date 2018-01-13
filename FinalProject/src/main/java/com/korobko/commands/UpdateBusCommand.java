package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.ATTR_NAME_FEEDBACK_MESSAGE;
import static com.korobko.utils.Constants.PATH_PAGE_DIRECTOR_FEEDBACK;
import static com.korobko.utils.Constants.ROWS_AFFECTED;

/**
 * @author Vova Korobko
 */
public class UpdateBusCommand implements Command {

    private static final String PARAM_NAME_OLD_VIN = "old_vin";
    private static final String MESSAGE_BUS_UPDATED = "message.bus.updated";
    private static final String MESSAGE_ERROR_UPDATE_BUS = "message.error.update.bus";

    @Override
    public String execute(HttpServletRequest request) {
        String newVin = request.getParameter(DBColumns.VIN);
        String registrNumber = request.getParameter(DBColumns.REGISTR_NUMBER);
        String modelId = request.getParameter(DBColumns.MODEL_ID);
        String oldVin = request.getParameter(PARAM_NAME_OLD_VIN);
        int result = BusService.INSTANCE.processBus(oldVin, newVin, registrNumber, modelId);
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_BUS));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
