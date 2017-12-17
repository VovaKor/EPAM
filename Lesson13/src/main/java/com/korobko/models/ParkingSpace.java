package com.korobko.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Vova Korobko
 */
public class ParkingSpace extends Place {

    private Lock lock;

    public ParkingSpace(long id) {
        super(id);
        lock = new ReentrantLock();
    }

    public boolean holdSpace() {
        return lock.tryLock();
    }

    public void releaseSpace() {
        lock.unlock();
    }
}
