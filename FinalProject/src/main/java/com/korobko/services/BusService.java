package com.korobko.services;

import com.korobko.dao.BusDao;
import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.entities.Bus;
import com.korobko.utils.Constants;
import com.korobko.utils.InputValidator;

import java.util.List;

/**
 * @author Vova Korobko
 */
public enum BusService {
    INSTANCE;
    private BusDao busDao;

    BusService() {
        this.busDao = (BusDao) DaoFactory.INSTANCE.getDao(DaoType.BUS);
    }

    public List<Bus> getAllBuses() {
        return busDao.getAllBuses();
    }

    public Bus getBusByVIN(String vin) {
        return busDao.getBusByVIN(vin);
    }

    /**
     * todo
     * @param routeNumber
     * @param vin
     * @return
     */
    public int updateBusRoute(String routeNumber, String vin) {
        if (InputValidator.nonInteger(routeNumber)) {
            return 0;
        }
        Integer number = Integer.valueOf(routeNumber);
        if (number == 0) {
            number = null;
        }
        return busDao.updateBusRoute(number, vin);
    }

    public List<String> getFreeBusIds() {
        return busDao.findFreeBusIds();
    }

    public int createBus(String vin, String registrNumber, String modelId) {
        if (InputValidator.isNullOrEmpty(vin, modelId)) {
            return Constants.ERROR_CODE;
        }
        return busDao.insertBus(vin, registrNumber, Long.valueOf(modelId));
    }

    public int processBus(String oldVin, String newVin, String registrNumber, String modelId) {
        if (InputValidator.isNullOrEmpty(oldVin, modelId)) {
            return Constants.ERROR_CODE;
        }
        if (newVin.isEmpty()) {
            return busDao.deleteBus(oldVin);
        }
        Long mId = Long.valueOf(modelId);
        if (newVin.equals(oldVin)) {
            return busDao.updateBusParams(oldVin, registrNumber, mId);
        }
        return busDao.updateFullBus(oldVin, newVin, registrNumber, mId);
    }
}
