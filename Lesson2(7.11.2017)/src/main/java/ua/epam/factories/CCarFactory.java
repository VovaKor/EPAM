package ua.epam.factories;

import ua.epam.vehicles.CCar;

import java.util.Random;

public class CCarFactory extends AbstractFactory {
    public static CCar create(){
        Random r = new Random();

        return new CCar(r.nextInt(100) + 100,r.nextInt(50) + 1968 ,r.nextInt(10000) + 60000,
                 r.nextInt(220),r.nextInt(8));
    }
}