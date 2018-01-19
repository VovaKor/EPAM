package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.AppointmentService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class UpdateDriverBusCommand implements Command {

    private static final String MESSAGE_DRIVER_BUS_UPDATED = "message.driver.bus.updated";
    private static final String MESSAGE_ERROR_UPDATE_DRIVER_BUS = "message.error.update.driver.bus";

    @Override
    public String execute(HttpServletRequest request) {
        String employeeId = request.getParameter(DBColumns.EMPLOYEE_ID);
        String busId = request.getParameter(DBColumns.BUS_ID);
        Long empId = Long.valueOf(employeeId);
        int result = AppointmentService.INSTANCE.executeUpdate(empId, busId);
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_DRIVER_BUS_UPDATED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_UPDATE_DRIVER_BUS));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_FEEDBACK);
    }
}
