package ua.epam.vehicles;

import ua.epam.interfaces.MoveAble;
import ua.epam.interfaces.SwimAble;

public class CAmphibianCar extends CVehicle implements SwimAble, MoveAble {
    public CAmphibianCar(int mechanismId, int year, int price,  int speed){
        super(mechanismId, year, price,  speed);
    }

}
