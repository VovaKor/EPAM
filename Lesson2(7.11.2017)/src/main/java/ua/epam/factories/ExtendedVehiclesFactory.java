package ua.epam.factories;

import ua.epam.vehicles.CVehicle;

public class ExtendedVehiclesFactory extends AbstractFactory{
    public static CVehicle createVehicle(){
        int key = (int)(Math.random()*5);
        switch(key){
            case 0:
                return CCarFactory.create();
            case 1:
                return CPlaneFactory.create();
            case 2:
                return CShipFactory.create();
            case 3:
                return CAmphibianCarFactory.create();
            case 4:
                return CBatMobileFactory.create();
            default:
                return null;
        }
    }
}
