package com.korobko;

import java.util.concurrent.*;

/**
 * б) Первая военная задача. Темной-темной ночью прапорщики Иванов, Петров и
 * Нечепорчук занимаются хищением военного имущества со склада родной военной
 * части. Будучи умными людьми и отличниками боевой и строевой подготовки,
 * прапорщики ввели разделение труда: Иванов выносит имущество со склада, Петров
 * грузит его в грузовик, а Нечепорчук подсчитывает рыночную стоимость добычи.
 * Требуется составить многопоточное приложение, моделирующее деятельность
 * прапорщиков. При решении использовать парадигму «производитель-потребитель»
 * с активным ожиданием.
 *
 * @author Vova Korobko
 */
public class TaskB {
    public static void main(String[] args) throws InterruptedException {
        Possessions possessions = new Possessions(10);

        Petrov petrov = new Petrov(possessions);
        Ivanov ivanov = new Ivanov(possessions);
        Necheporchuk necheporchuk = new Necheporchuk(possessions);

        // Starting threads
        ivanov.start();
        petrov.start();
        necheporchuk.start();
    }
}

class Possessions {
    private int storageAmount;
    private BlockingQueue<Integer> storage;
    private BlockingQueue<Integer> stolenThings;
    private BlockingQueue<Integer> truck;
    private BlockingQueue<Integer> calculatedThings;

    public Possessions(int storageAmount) throws InterruptedException {
        this.storageAmount = storageAmount;
        this.storage = new LinkedBlockingQueue<>(storageAmount);
        for (int i = 1; i <= storageAmount; i++) {
            storage.put(i);
        }
        this.stolenThings = new LinkedBlockingQueue<>();
        this.truck = new LinkedBlockingQueue<>();
        this.calculatedThings = new LinkedBlockingQueue<>();
    }

    public int getStorageAmount() {
        return storageAmount;
    }

    public BlockingQueue<Integer> getStorage() {
        return storage;
    }

    public BlockingQueue<Integer> getStolenThings() {
        return stolenThings;
    }

    public BlockingQueue<Integer> getTruck() {
        return truck;
    }

    public BlockingQueue<Integer> getCalculatedThings() {
        return calculatedThings;
    }

}

class Ivanov extends Thread {
    private Possessions possessions;

    public Ivanov(Possessions possessions) {
        this.possessions = possessions;
    }

    @Override
    public void run() {
        try {
            while (!possessions.getStorage().isEmpty()) {
                Integer thing = possessions.getStorage().take();
                System.out.println("Ivanov is stealing " + thing);
                TimeUnit.MILLISECONDS.sleep(100);
                possessions.getStolenThings().put(thing);
            }
            System.out.println("Ivanov has stolen " + possessions.getStorageAmount() + " things");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Petrov extends Thread {
    private Possessions possessions;

    public Petrov(Possessions possessions) {
        this.possessions = possessions;
    }

    @Override
    public void run() {
        try {
            int loadedThings = 0;
            while (loadedThings < possessions.getStorageAmount()) {
                Integer thing = possessions.getStolenThings().take();
                System.out.println("Petrov is loading " + thing);
                TimeUnit.MILLISECONDS.sleep(100);
                possessions.getTruck().put(thing);
                loadedThings++;
            }
            System.out.println("Petrov has loaded " + possessions.getStorageAmount() + " things");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

class Necheporchuk extends Thread {
    private Possessions possessions;
    private int price;

    public Necheporchuk(Possessions possessions) {
        this.possessions = possessions;
    }

    @Override
    public void run() {
        try {
            while (possessions.getCalculatedThings().size() < possessions.getStorageAmount()) {
                Integer thing = possessions.getTruck().take();
                price += thing;
                System.out.println("Necheporchuk has calculated the price = " + price);
                possessions.getCalculatedThings().put(thing);
            }

            System.out.println("Total price = " + price);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
