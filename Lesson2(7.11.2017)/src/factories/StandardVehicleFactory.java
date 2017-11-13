package factories;

import vehicles.CVehicle;

public class StandardVehicleFactory {

    public static CVehicle createVehicle(){
        int key = (int)(Math.random()*3);
        switch(key){
            case 0:
                return new CCarConcreteFactory().create();
            case 1:
                return new CPlaneConcreteFactory().create();
            case 2:
                return new CShipConcreteFactory().create();
            default:
                return null;
        }
    }
}
