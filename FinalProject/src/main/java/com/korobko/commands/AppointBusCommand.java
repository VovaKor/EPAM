package com.korobko.commands;

import com.korobko.dao.DBColumns;
import com.korobko.entities.Appointment;
import com.korobko.services.AppointmentService;
import com.korobko.services.BusService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.korobko.utils.Constants.ATTR_NAME_APPOINTMENT;

/**
 * @author Vova Korobko
 */
public class AppointBusCommand implements Command {
    private static final String ATTR_NAME_BUSES = "id_list";
    private static final String PATH_PAGE_APPOINT_BUS = "path.page.appoint_bus";

    @Override
    public String execute(HttpServletRequest request) {
        String employeeId = request.getParameter(DBColumns.EMPLOYEE_ID);
        String appointmentId = request.getParameter(DBColumns.APPOINTMENT_ID);
        Appointment appointment = AppointmentService.INSTANCE.getPoorAppointment(appointmentId, employeeId);
        List<String> busIds = BusService.INSTANCE.getFreeBusIds();
        request.setAttribute(ATTR_NAME_BUSES, busIds);
        request.setAttribute(ATTR_NAME_APPOINTMENT, appointment);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_APPOINT_BUS);
    }
}
