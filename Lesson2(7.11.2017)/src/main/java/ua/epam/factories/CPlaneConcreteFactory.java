package ua.epam.factories;

import ua.epam.vehicles.CPlane;

import java.util.Random;

public class CPlaneConcreteFactory extends AbstractConcreteFactory {
    public CPlane create(){
        Random r = new Random();

        return new CPlane(r.nextInt(1000) + 1000, r.nextInt(30) + 1988,
                r.nextInt(100000000),r.nextInt(500), r.nextInt(250),r.nextInt(15000));
    }
}