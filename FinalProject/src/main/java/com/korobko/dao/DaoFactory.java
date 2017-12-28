package com.korobko.dao;

import com.korobko.ApplicationProperties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
     * @throws Exception
     */
    public Dao getDao(DaoType daoType) throws Exception {
        Dao dao = daoMap.get(daoType);
        if (dao != null) {
            return dao;
        } else {
            String packageName = Dao.class.getPackage().getName();
            Constructor constructor = Class.forName(packageName + "." + daoType.getName()).getConstructor(Connection.class);
            dao = (Dao) constructor.newInstance(createConnection());
            daoMap.put(daoType, dao);
            return dao;
        }
    }

    private Connection createConnection() throws SQLException, NamingException {

        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource) envCtx.lookup(ApplicationProperties.DATABASE_JNDI_NAME);
        return ds.getConnection();
    }

}
