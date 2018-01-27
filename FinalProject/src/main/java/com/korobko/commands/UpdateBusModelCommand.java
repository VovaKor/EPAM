package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.BusModelService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class UpdateBusModelCommand implements Command {

    private static final String MESSAGE_ERROR_UPDATE_BUS_MODEL = "message.error.update.bus.model";
    private static final String MESSAGE_BUS_MODEL_UPDATED = "message.bus.model.updated";

    @Override
    public String execute(HttpServletRequest request) {
        String modelId = request.getParameter(DBColumns.MODEL_ID);
        String modelName = request.getParameter(DBColumns.MODEL_NAME);
        Long mId = Long.valueOf(modelId);
        if (ROWS_AFFECTED == BusModelService.INSTANCE.processBusModel(mId, modelName)) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_BUS_MODEL_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_BUS_MODEL));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DIRECTOR_FEEDBACK);
    }
}
