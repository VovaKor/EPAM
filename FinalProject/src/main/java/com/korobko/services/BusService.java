package com.korobko.services;

import com.korobko.dao.BusDao;
import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.entities.Bus;

import java.util.List;

import static com.korobko.utils.Constants.ROWS_PER_PAGE;

/**
 * @author Vova Korobko
 */
public enum BusService {
    INSTANCE;
    private BusDao busDao;

    BusService() {
        this.busDao = (BusDao) DaoFactory.INSTANCE.getDao(DaoType.BUS);
    }

    public List<Bus> getAllBuses(int requestedPage) {
        return busDao.getAllBuses(PaginationService.INSTANCE.calculateOffset(requestedPage), ROWS_PER_PAGE);
    }

    public Bus getBusByVIN(String vin) {
        return busDao.getBusByVIN(vin);
    }

    public int updateBusRoute(Integer routeNumber, String vin) {
        return busDao.updateBusRoute(routeNumber, vin);
    }

    public List<String> getFreeBusIds() {
        return busDao.findFreeBusIds();
    }

    public int createBus(String vin, String registrNumber, String modelId) {
        return busDao.insertBus(vin, registrNumber, Long.valueOf(modelId));
    }

    /**
     * <p>Calls {@code BusDao.deleteBus} method when {@param newVin}
     * is empty.
     * <p>Calls {@code BusDao.updateBusParams} method when {@param newVin}
     * equals {@param oldVin} or {@code BusDao.updateFullBus} if they not equals.
     *
     * @param oldVin the {@code String} value {@code Bus} VIN
     * @param newVin the {@code String} value {@code Bus} VIN to insert
     * @param registrNumber the {@code String} value {@code Bus} registration number
     * @param modelId the {@code Long} value {@code BusModel} id
     * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
     *         or (2) 0 if exception happened
     */
    public int processBus(String oldVin, String newVin, String registrNumber, String modelId) {

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
