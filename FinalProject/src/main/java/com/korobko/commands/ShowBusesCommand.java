package com.korobko.commands;

import com.korobko.entities.Bus;
import com.korobko.entities.BusModel;
import com.korobko.services.BusModelService;
import com.korobko.services.BusService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.korobko.utils.Constants.ATTR_NAME_BUS_LIST;
import static com.korobko.utils.Constants.ATTR_NAME_MODEL_LIST;

/**
 * @author Vova Korobko
 */
public class ShowBusesCommand implements Command {

    private static final String PATH_PAGE_BUSES = "path.page.buses";

    @Override
    public String execute(HttpServletRequest request) {
        List<Bus> buses = BusService.INSTANCE.getAllBuses();
        List<BusModel> models = BusModelService.INSTANCE.getAllBusModels();
        request.setAttribute(ATTR_NAME_BUS_LIST, buses);
        request.setAttribute(ATTR_NAME_MODEL_LIST, models);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_BUSES);
    }
}
