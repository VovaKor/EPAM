package com.korobko.factories;

import com.korobko.models.ParkingSpace;

/**
 * @author Vova Korobko
 */
public class ParkingSpaceFactory implements Factory<ParkingSpace> {

    private static long spaceId;

    public ParkingSpace create() {
        return new ParkingSpace(spaceId++);
    }
}
