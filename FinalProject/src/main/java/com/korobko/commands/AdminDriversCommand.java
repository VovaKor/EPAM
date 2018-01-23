package com.korobko.commands;

import com.korobko.entities.Appointment;
import com.korobko.services.AppointmentService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Vova Korobko
 */
public class AdminDriversCommand implements Command {

    private static final String ATTR_NAME_APP_LIST = "app_list";
    private static final String PATH_PAGE_ADMIN_DRIVERS = "path.page.admin_drivers";

    @Override
    public String execute(HttpServletRequest request) {        
        List<Appointment> appointments = AppointmentService.INSTANCE.getAppointmentsIncludingFreeDrivers();
        request.setAttribute(ATTR_NAME_APP_LIST, appointments);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_DRIVERS);
    }
}
