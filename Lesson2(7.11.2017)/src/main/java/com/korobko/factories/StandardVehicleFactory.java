package com.korobko.factories;

import com.korobko.vehicles.CVehicle;

public class StandardVehicleFactory extends AbstractFactory{

    public static CVehicle createVehicle(){
        int key = (int)(Math.random()*3);
        switch(key){
            case 0:
                return CCarFactory.create();
            case 1:
                return CPlaneFactory.create();
            case 2:
                return CShipFactory.create();
            default:
                return null;
        }
    }
}
