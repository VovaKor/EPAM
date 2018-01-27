package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusModelService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class CreateBusModelCommand implements Command {

    private static final String MESSAGE_ERROR_CREATE_BUS_MODEL = "message.error.create.bus.model";
    private static final String MESSAGE_BUS_MODEL_CREATED = "message.bus.model.created";

    @Override
    public String execute(HttpServletRequest request) {
        String modelName = request.getParameter(DBColumns.MODEL_NAME);
        int result = 0;
        if (InputValidator.nonNullnotEmpty(modelName)) {
            result = BusModelService.INSTANCE.createBusModel(modelName);
        }

        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_MODEL_CREATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_CREATE_BUS_MODEL));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
