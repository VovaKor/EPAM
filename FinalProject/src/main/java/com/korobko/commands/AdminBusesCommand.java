package com.korobko.commands;

import com.korobko.entities.Bus;
import com.korobko.services.BusService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.korobko.utils.Constants.ATTR_NAME_BUS_LIST;

/**
 * @author Vova Korobko
 */
public class AdminBusesCommand implements Command {

    private static final String PATH_PAGE_ADMIN_BUSES = "path.page.admin_buses";

    @Override
    public String execute(HttpServletRequest request) {
        List<Bus> buses = BusService.INSTANCE.getAllBuses();
        request.setAttribute(ATTR_NAME_BUS_LIST, buses);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_BUSES);
    }
}
