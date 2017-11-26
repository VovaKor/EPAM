package ua.epam.factories;

import ua.epam.vehicles.CAmphibianCar;

import java.util.Random;

public class CAmphibianCarFactory extends AbstractFactory {

    public static CAmphibianCar create(){
        Random r = new Random();

        return new CAmphibianCar(r.nextInt(200) + 3001,r.nextInt(47) + 1970 ,r.nextInt(10000) + 60000,
                r.nextInt(120));
    }
}
