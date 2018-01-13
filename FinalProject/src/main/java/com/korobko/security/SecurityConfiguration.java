package com.korobko.security;

import com.korobko.commands.CommandEnum;
import com.korobko.entities.EmployeePosition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Vova Korobko
 */
public enum SecurityConfiguration {
    INSTANCE;
    public static final String SECURITY_LEVEL_ALL = "ALL";
    public static final String SECURITY_LEVEL_AUTH = "AUTHORIZED";
    public final String SECURITY_LEVEL_ADMIN = EmployeePosition.ADMIN.name();
    public final String SECURITY_LEVEL_DRIVER = EmployeePosition.DRIVER.name();
    public final String SECURITY_LEVEL_DIRECTOR = EmployeePosition.DIRECTOR.name();
    private Map<CommandEnum, String> grant = new HashMap<>();

    SecurityConfiguration() {
        grant.put(CommandEnum.LOGIN, SECURITY_LEVEL_ALL);
        grant.put(CommandEnum.REGISTER, SECURITY_LEVEL_ALL);
        grant.put(CommandEnum.SHOW_SIGNUP_VIEW, SECURITY_LEVEL_ALL);
        grant.put(CommandEnum.PROFILE, SECURITY_LEVEL_AUTH);
        grant.put(CommandEnum.UPDATE_PROFILE, SECURITY_LEVEL_AUTH);
        grant.put(CommandEnum.LOGOUT, SECURITY_LEVEL_AUTH);
        grant.put(CommandEnum.ADMIN_DRIVERS, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.ADMIN_BUSES, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.UPDATE_BUS_ROUTE, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.APPOINT_ROUTE, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.APPOINT_BUS, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.UPDATE_DRIVER_BUS, SECURITY_LEVEL_ADMIN);
        grant.put(CommandEnum.SHOW_APPOINTMENT, SECURITY_LEVEL_DRIVER);
        grant.put(CommandEnum.APPROVE_APPOINTMENT, SECURITY_LEVEL_DRIVER);
        grant.put(CommandEnum.SHOW_EMPLOYEES, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.SHOW_ROUTES, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.UPDATE_POSITION, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.CREATE_ROUTE, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.UPDATE_ROUTE, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.SHOW_BUS_MODELS, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.CREATE_BUS_MODEL, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.UPDATE_BUS_MODEL, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.SHOW_BUSES, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.CREATE_BUS, SECURITY_LEVEL_DIRECTOR);
        grant.put(CommandEnum.UPDATE_BUS, SECURITY_LEVEL_DIRECTOR);
    }

    public String getSecurityLevel(String command){
        if (Objects.isNull(command) || command.isEmpty()) {
            return null;
        }
        return grant.get(CommandEnum.valueOf(command.toUpperCase()));
    }
}
