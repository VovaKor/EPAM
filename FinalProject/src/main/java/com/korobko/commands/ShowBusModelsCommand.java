package com.korobko.commands;

import com.korobko.entities.BusModel;
import com.korobko.services.BusModelService;
import com.korobko.utils.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.korobko.utils.Constants.ATTR_NAME_MODEL_LIST;

/**
 * @author Vova Korobko
 */
public class ShowBusModelsCommand implements Command {

    private static final String PATH_PAGE_BUS_MODELS = "path.page.bus.models";

    @Override
    public String execute(HttpServletRequest request) {
        List<BusModel> models = BusModelService.INSTANCE.getAllBusModels();
        request.setAttribute(ATTR_NAME_MODEL_LIST, models);
        return ResourceManager.CONFIGURATION.getProperty(PATH_PAGE_BUS_MODELS);
    }
}
