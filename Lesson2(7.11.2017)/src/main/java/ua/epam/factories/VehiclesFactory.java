package ua.epam.factories;

import ua.epam.vehicles.CVehicle;

public class VehiclesFactory {
    public static CVehicle createVehicle(){
        int key = (int)(Math.random()*5);
        switch(key){
            case 0:
                return new CCarConcreteFactory().create();
            case 1:
                return new CPlaneConcreteFactory().create();
            case 2:
                return new CShipConcreteFactory().create();
            case 3:
                return new CAmphibianCarConcreteFactory().create();
            case 4:
                return new CBatMobileConcreteFactory().create();
            default:
                return null;
        }
    }
}
