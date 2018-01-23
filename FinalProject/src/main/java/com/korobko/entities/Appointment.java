package com.korobko.entities;

import java.time.LocalDateTime;

/**
 * @author Vova Korobko
 */
public class Appointment {
    private Long appointmentId;
    private Employee employee;
    private Bus bus;
    private LocalDateTime created;
    private LocalDateTime approved;

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getApproved() {
        return approved;
    }

    public void setApproved(LocalDateTime approved) {
        this.approved = approved;
    }
}
