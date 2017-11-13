package factories;

import vehicles.CCar;

import java.util.Random;

public class CCarConcreteFactory extends AbstractConcreteFactory {
    public CCar create(){
        Random r = new Random();

        return new CCar(r.nextInt(100) + 100,r.nextInt(50) + 1968 ,r.nextInt(10000) + 60000,
                 r.nextInt(220),r.nextInt(8));
    }
}