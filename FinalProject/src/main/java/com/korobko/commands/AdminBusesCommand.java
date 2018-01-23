package com.korobko.commands;

import com.korobko.dao.DaoType;
import com.korobko.entities.Bus;
import com.korobko.services.BusService;
import com.korobko.services.PaginationService;
import com.korobko.utils.InputValidator;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.korobko.utils.Constants.*;

/**
 * @author Vova Korobko
 */
public class AdminBusesCommand implements Command {

    private static final String PATH_PAGE_ADMIN_BUSES = "path.page.admin_buses";

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getParameter(PARAM_NAME_PAGE);
        int requestedPage = 1;
        if(InputValidator.isPositiveInteger(page)) {
            requestedPage = Integer.valueOf(page);
        }
        List<Bus> buses = BusService.INSTANCE.getAllBuses(requestedPage);
        request.setAttribute(ATTR_NAME_CURRENT_PAGE, requestedPage);
        request.setAttribute(ATTR_NAME_PAGE_AMOUNT, PaginationService.INSTANCE.getPageAmount(DaoType.BUS));
        request.setAttribute(PARAM_NAME_COMMAND, CommandEnum.ADMIN_BUSES.name());
        request.setAttribute(ATTR_NAME_BUS_LIST, buses);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_ADMIN_BUSES);
    }
}
