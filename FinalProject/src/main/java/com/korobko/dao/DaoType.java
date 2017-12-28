package com.korobko.dao;

/**
 * @author Vova Korobko
 */
public enum DaoType {
    BUS("BusDao"), EMPLOYEE("EmployeeDao");

    private String name;

    DaoType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
