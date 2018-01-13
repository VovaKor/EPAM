package com.korobko.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum DaoFactory {

    INSTANCE;

    private Map<DaoType, Dao> daoMap = new HashMap<>();

    /**
     * //todo
     * @param daoType
     * @return
     */
    public synchronized Dao getDao(DaoType daoType) {
        Dao dao = daoMap.get(daoType);
        if (Objects.nonNull(dao)) {
            return dao;
        } else {
            String packageName = Dao.class.getPackage().getName();
            try {
                dao = (Dao) Class.forName(packageName + "." + daoType.getName()).newInstance();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                //todo
            }
            daoMap.put(daoType, dao);
            return dao;
        }
    }
}
