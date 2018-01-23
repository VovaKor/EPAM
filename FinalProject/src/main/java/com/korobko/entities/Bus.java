package com.korobko.entities;

/**
 * @author Vova Korobko
 */
public class Bus {
    private String VIN;
    private String registrationNumber;
    private BusModel busModel;
    private Route route;

    public BusModel getBusModel() {
        return busModel;
    }

    public void setBusModel(BusModel busModel) {
        this.busModel = busModel;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
