package com.korobko.entities;

/**
 * @author Vova Korobko
 */
public class Employee {
    private Long employeeId;
    private EmployeePosition position;
    private String email;
    private String password;
    private Names names;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public EmployeePosition getPosition() {
        return position;
    }

    public void setPosition(EmployeePosition position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Names getNames() {
        return names;
    }

    public void setNames(Names names) {
        this.names = names;
    }
}
