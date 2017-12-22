package com.korobko.vehicles;

import com.korobko.interfaces.MoveAble;
import com.korobko.interfaces.SwimAble;

public class CAmphibianCar extends CVehicle implements SwimAble, MoveAble {
    public CAmphibianCar(int mechanismId, int year, int price,  int speed){
        super(mechanismId, year, price,  speed);
    }

}
