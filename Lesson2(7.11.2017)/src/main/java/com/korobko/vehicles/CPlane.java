package com.korobko.vehicles;

import com.korobko.interfaces.FlyAble;

public class CPlane extends CVehicle implements FlyAble {
    private int passengers;
    private int maxAltitude;

    public CPlane(int mechanismId, int year, int price,  int speed, int passengers, int maxAltitude){
        super(mechanismId, year, price,  speed);
        this.passengers = passengers;
        this.maxAltitude = maxAltitude;

    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    public int getMaxAltitude() {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude) {
        this.maxAltitude = maxAltitude;
    }

    @Override
    public String toString() {
        return "CPlane{" +
                "maxAltitude=" + maxAltitude +
                ", year=" + getYear() +
                '}';
    }
}