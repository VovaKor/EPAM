package com.korobko.factories;

import com.korobko.models.Car;

/**
 * @author Vova Korobko
 */
public class CarFactory implements Factory<Car> {
    private static long carId;
    public Car create() {
        return new Car(carId++);
    }
}
