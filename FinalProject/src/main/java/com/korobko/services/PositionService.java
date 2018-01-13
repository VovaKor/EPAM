package com.korobko.services;

import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;
import com.korobko.dao.RolesDao;
import com.korobko.entities.EmployeePosition;

import java.util.List;

/**
 * @author Vova Korobko
 */
public enum PositionService {
    INSTANCE;

    private RolesDao rolesDao;

    PositionService() {
        this.rolesDao = (RolesDao) DaoFactory.INSTANCE.getDao(DaoType.ROLES);
    }

    public List<EmployeePosition> getSubordinatePositions() {
        return rolesDao.getSubordinatePositions();
    }
}
