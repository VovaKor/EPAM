package com.korobko;

import java.util.ArrayList;
import java.util.List;

/**
 * а)Первая задача о Винни-Пухе, или неправильные пчелы. Неправильные пчелы,
 * подсчитав в конце месяца убытки от наличия в лесу Винни-Пуха, решили
 * разыскать его и наказать в назидание всем другим любителям сладкого. Для
 * поисков медведя они поделили лес на участки, каждый из которых прочесывает
 * одна стая неправильных пчел. В случае нахождения медведя на своем участке
 * стая проводит показательное наказание и возвращается в улей.Если участок
 * прочесан, а Винни-Пух на нем не обнаружен, стая также возвращается в улей.
 * Требуется создать многопоточное приложение, моделирующее действия пчел. При
 * решении использовать парадигму портфеля задач.
 *
 * @author Vova Korobko
 */
public class TaskA {
    public static void main(String[] args) {
        Forest forest = new Forest();
        BeeFlock beeFlock1 = new BeeFlock("Flock#1", forest);
        BeeFlock beeFlock2 = new BeeFlock("Flock#2", forest);
        BeeFlock beeFlock3 = new BeeFlock("Flock#3", forest);

        beeFlock1.start();
        beeFlock2.start();
        beeFlock3.start();

    }
}

class BeeFlock extends Thread {
    private Forest forest;

    public BeeFlock(String flockName, Forest forest) {
        super(flockName);
        this.forest = forest;
    }

    @Override
    public void run() {
        while (!forest.isBearFound()) {

            Integer[] sector = forest.getNextSector();
            if (sector == null) {
                System.out.println(this.getName() + " - There are no sectors to search. Dismissing...");
                return;
            }

            System.out.println(this.getName() + " - searching new sector " + forest.getSectorIndex());

            for (Integer i : sector) {
                // simulating long work
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (i == 1) {
                    forest.setBearFound(true);
                    System.out.println(this.getName() + " - Bear was found and punished!\n Rolling home.");
                    return;
                }
            }
            System.out.println(this.getName() + " - Bear wasn't found");
        }
        System.out.println(this.getName() + " returned home.");
    }
}

class Forest {
    private List<Integer[]> sectors;
    private int sectorIndex;
    private boolean isBearFound;
    public Forest() {
        this.sectors = new ArrayList<>();
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        sectors.add(new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0});

    }

    public synchronized Integer[] getNextSector() {

        if (sectorIndex < sectors.size()) {
            return sectors.get(sectorIndex++);
        }

        return null;
    }

    public boolean isBearFound() {
        return isBearFound;
    }

    public void setBearFound(boolean bearFound) {
        isBearFound = bearFound;
    }

    public int getSectorIndex() {
        return sectorIndex;
    }
}