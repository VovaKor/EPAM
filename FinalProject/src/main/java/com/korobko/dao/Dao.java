package com.korobko.dao;

import java.sql.Connection;

/**
 * @author Vova Korobko
 */
abstract class Dao {
    private Connection connection;

    Dao(Connection connection) {
        this.connection = connection;
    }
}
