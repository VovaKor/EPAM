package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.services.AppointmentService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class ApproveAppointmentCommand implements Command {

    private static final String MESSAGE_ERROR_APPROVE_APPOINTMENT = "message.error.approve.appointment";
    private static final String MESSAGE_APPOINTMENT_APPROVED = "message.appointment.approved";

    @Override
    public String execute(HttpServletRequest request) {
        String appId = request.getParameter(DBColumns.APPOINTMENT_ID);
        int result = AppointmentService.INSTANCE.approveAppointment(appId);
        if (result == ROWS_AFFECTED) {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_APPOINTMENT_APPROVED));
        } else {
            request.setAttribute(ATTR_NAME_FEEDBACK_MESSAGE, ResourceManager.MESSAGES.getProperty(MESSAGE_ERROR_APPROVE_APPOINTMENT));
        }
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_DRIVER_FEEDBACK);
    }
}
