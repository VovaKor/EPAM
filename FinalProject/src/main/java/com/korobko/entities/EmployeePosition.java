package com.korobko.entities;

/**
 * @author Vova Korobko
 */
public enum EmployeePosition {
    ADMIN(1), DRIVER(2), DIRECTOR(3);

    private int role_id;
    EmployeePosition(int positionId) {
        this.role_id = positionId;
    }

    public int getRole_id() {
        return role_id;
    }
}
