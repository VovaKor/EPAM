package com.korobko.dao;

/**
 * @author Vova Korobko
 */
public enum DaoType {
    BUS("BusDao"),
    EMPLOYEE("EmployeeDao"),
    ROUTE("RouteDao"),
    APPOINTMENT("AppointmentDao"),
    NAMES("NamesDao"),
    ROLES("RolesDao"),
    BUS_MODEL("BusModelDao");

    private String name;

    DaoType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
