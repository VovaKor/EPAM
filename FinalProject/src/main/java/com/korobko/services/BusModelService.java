package com.korobko.services;

import com.korobko.dao.BusModelDao;
import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.entities.BusModel;
import com.korobko.utils.Constants;
import com.korobko.utils.InputValidator;

import java.util.List;

/**
 * @author Vova Korobko
 */
public enum BusModelService {
    INSTANCE;

    private BusModelDao busModelDao;
    BusModelService() {
        this.busModelDao = (BusModelDao) DaoFactory.INSTANCE.getDao(DaoType.BUS_MODEL);
    }

    public List<BusModel> getAllBusModels() {
        return busModelDao.getBusModels();
    }

    public int createBusModel(String modelName) {
        if (InputValidator.isNullOrEmpty(modelName)) {
            return Constants.ERROR_CODE;
        }
        return busModelDao.insertBusModel(modelName);
    }

    public int processBusModel(String modelId, String modelName) {
        Long mId = Long.valueOf(modelId);
        if (modelName.isEmpty()) {
            return busModelDao.deleteBusModel(mId);
        }
        return busModelDao.updateBusModel(mId, modelName);
    }
}
