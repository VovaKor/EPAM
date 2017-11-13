package factories;

import vehicles.CShip;

import java.util.Random;

public class CShipConcreteFactory extends AbstractConcreteFactory {
    public CShip create() {
        Random r = new Random();

        return new CShip(r.nextInt(200) + 2001,r.nextInt(47) + 1970 ,r.nextInt(10000) + 60000,
                r.nextInt(320),"Harbor port");
    }
}
