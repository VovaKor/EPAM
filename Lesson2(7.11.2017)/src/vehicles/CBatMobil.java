package vehicles;

import interfaces.FlyAble;
import interfaces.MoveAble;
import interfaces.SwimAble;

public class CBatMobil extends CVehicle implements SwimAble, MoveAble, FlyAble {

    public CBatMobil(int mechanismId, int year, int price,  int speed){
        super(mechanismId, year, price,  speed);
    }


}