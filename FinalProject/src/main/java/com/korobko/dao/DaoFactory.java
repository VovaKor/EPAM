package com.korobko.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum DaoFactory {

    INSTANCE;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private Map<DaoType, Dao> daoMap = new ConcurrentHashMap<>();

    /**
     * <p>Attempts to get a reference to dao object of given daoType.
     * If there is no appropriate dao object, then instantiates one.
     *
     * @param daoType a type of dao to retrieve
     * @return a dao object of given daoType
     */
    public Dao getDao(DaoType daoType) {
        Dao dao = daoMap.get(daoType);
        if (Objects.nonNull(dao)) {
            return dao;
        } else {
            String packageName = Dao.class.getPackage().getName();
            try {
                dao = (Dao) Class.forName(packageName + "." + daoType.getName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                logger.error("Exception while getting dao", e);
            }
            daoMap.put(daoType, dao);
            return dao;
        }
    }
}
