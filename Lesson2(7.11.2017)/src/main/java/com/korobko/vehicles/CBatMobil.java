package com.korobko.vehicles;

import com.korobko.interfaces.FlyAble;
import com.korobko.interfaces.MoveAble;
import com.korobko.interfaces.SwimAble;

public class CBatMobil extends CVehicle implements SwimAble, MoveAble, FlyAble {

    public CBatMobil(int mechanismId, int year, int price,  int speed){
        super(mechanismId, year, price,  speed);
    }


}