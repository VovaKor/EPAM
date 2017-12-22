package com.korobko.vehicles;

import com.korobko.interfaces.MoveAble;

public class CCar extends CVehicle implements MoveAble {
    private int passengers = 0;

    public CCar(int mechanismId, int year, int price, int speed, int passengers){
        super(mechanismId, year, price,  speed);
        this.passengers = passengers;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
}
