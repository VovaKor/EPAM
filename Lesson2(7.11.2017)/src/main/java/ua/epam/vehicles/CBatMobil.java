package ua.epam.vehicles;

import ua.epam.interfaces.FlyAble;
import ua.epam.interfaces.MoveAble;
import ua.epam.interfaces.SwimAble;

public class CBatMobil extends CVehicle implements SwimAble, MoveAble, FlyAble {

    public CBatMobil(int mechanismId, int year, int price,  int speed){
        super(mechanismId, year, price,  speed);
    }


}