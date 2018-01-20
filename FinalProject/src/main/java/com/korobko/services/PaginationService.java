package com.korobko.services;

import com.korobko.dao.Dao;
import com.korobko.dao.DaoFactory;
import com.korobko.dao.DaoType;

import static com.korobko.utils.Constants.ROWS_PER_PAGE;

/**
 * @author Vova Korobko
 */
public enum PaginationService {
    INSTANCE;

    public int getPageAmount(DaoType daoType) {
        Dao dao = DaoFactory.INSTANCE.getDao(daoType);
        return (int) Math.ceil(dao.getRowCount() * 1.0 / ROWS_PER_PAGE);
    }

    public int calculateOffset(int requestedPage) {
        int offset = (requestedPage - 1) * ROWS_PER_PAGE;
        return offset < 0 ? -offset : offset;
    }
}
