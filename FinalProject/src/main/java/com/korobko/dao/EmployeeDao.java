package com.korobko.dao;

import java.sql.Connection;

/**
 * @author Vova Korobko
 */
class EmployeeDao extends Dao {

    EmployeeDao(Connection connection) {
        super(connection);
    }
}
