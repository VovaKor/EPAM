package factories;

import vehicles.CAmphibianCar;

import java.util.Random;

public class CAmphibianCarConcreteFactory extends AbstractConcreteFactory {

    public CAmphibianCar create(){
        Random r = new Random();

        return new CAmphibianCar(r.nextInt(200) + 3001,r.nextInt(47) + 1970 ,r.nextInt(10000) + 60000,
                r.nextInt(120));
    }
}
