package com.korobko.models;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.korobko.ApplicationProperties.*;

/**
 * @author Vova Korobko
 */
public class Car extends Entity implements Runnable {

    private Parking parking;

    public Car(long carID) {
        super(carID);
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void run() {
        int parkingAttempt = 0;
        try {
            while (!Thread.interrupted() && parkingAttempt < MAX_ATTEMPTS_TO_PARK) {

                boolean parkingResult = tryPark();

                if (parkingResult) {
                    parkingAttempt = 0;
                } else {

                    // Waiting for free space
                    parkingAttempt++;
                    TimeUnit.MILLISECONDS.sleep(MAX_WAIT_TIME);
                }
            }
            System.out.println(String.format("The car #%s has gone to another parking.", this.getId()));
        } catch (InterruptedException | RuntimeException e) {
            // Ignoring exceptions and getting clear output
            // e.printStackTrace();
        }
    }

    /**
     * Park for fixed amount of time
     * @return {@code true} if attempt was successful,
     *         {@code false} if there were no free spaces
     */
    private boolean tryPark() {
        return parking.getParkingSpaces()
                .stream()
                .filter(ParkingSpace::holdSpace)
                .findFirst()
                .map(parkingSpace -> {

                    System.out.println(String.format("The car #%s is holding space #%s.", this.getId(), parkingSpace.getId()));

                    try {
                        TimeUnit.MILLISECONDS.sleep(MAX_HOLD_TIME);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    parkingSpace.releaseSpace();
                    System.out.println(String.format("The car #%s has left space #%s.", this.getId(), parkingSpace.getId()));

                    return true;
                })
                .orElseGet(() -> false);
    }

}
