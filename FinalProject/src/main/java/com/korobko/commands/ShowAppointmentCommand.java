package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Appointment;
import com.korobko.services.AppointmentService;
import com.korobko.utils.Constants;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Vova Korobko
 */
public class ShowAppointmentCommand implements Command {

    private static final String PATH_PAGE_APPOINTMENT = "path.page.appointment";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Long employeeId = (Long) session.getAttribute(DBColumns.EMPLOYEE_ID);
        Appointment appointment = AppointmentService.INSTANCE.findAppointment(employeeId);
        request.setAttribute(Constants.ATTR_NAME_APPOINTMENT, appointment);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_APPOINTMENT);
    }
}
