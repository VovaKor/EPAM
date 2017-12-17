package com.korobko;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * с) Задача о Пути Кулака. На седых склонах Гималаев стоят два древних
 * буддистских монастыря: Гуань-Инь и Гуань-Янь. Каждый год в день сошествия
 * на землю боддисатвы Араватти монахи обоих монастырей собираются на совместное
 * празднество и показывают свое совершенствование на Пути Кулака. Всех
 * соревнующихся монахов разбивают на пары, победители пар бьются затем между
 * собой и так далее, до финального поединка. Монастырь, монах которого победил
 * в финальном бою, забирает себе на хранение статую боддисатвы. Реализовать
 * многопоточное приложение, определяющего победителя. В качестве входных
 * данных используется массив, в котором хранится количество энергии Ци каждого
 * монаха. При решении использовать принцип дихотомии.
 *
 * @author Vova Korobko
 */
public class TaskC {
    public static void main(String[] args) {
        List<Thread> duels = new ArrayList<>();
        Monastery monastery = new Monastery();
        while (monastery.getMonks().size() > 1) {
            System.out.println("Round -------------------- ");
            duels.clear();
            int size = monastery.getMonks().size();
            for (int i = 0; i < size; i += 2) {

                Thread thread = new Thread(new Fight(monastery, i, 1 + i), "Fight#" + i/2);
                duels.add(thread);
                thread.start();

            }

            duels.stream().forEach(thread -> {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            monastery.setMonks(monastery.getWinners());

        }

        System.out.println("Winner = " + monastery.getMonks().get(0));
    }

}

class Monastery {
    private List<Integer> monks;
    private List<Integer> winners;

    public Monastery() {
        this.monks = Arrays.asList(48, 50, 1, 2, 13, 47, 56, 40);
        this.winners = new ArrayList<>();
    }

    public List<Integer> getWinners() {
        return winners;
    }

    public synchronized void addMonkToWinners(Integer winner) {
        this.winners.add(winner);
    }

    public List<Integer> getMonks() {
        return monks;
    }

    public synchronized void setMonks(List<Integer> monks) {
        this.monks = monks;
        this.winners = new ArrayList<>();
    }
}

class Fight implements Runnable {

    private Monastery monastery;
    private int firstMonkIndex;
    private int secondMonkIndex;


    public Fight(Monastery monastery, int firstMonkIndex, int secondMonkIndex) {
        this.monastery = monastery;
        this.firstMonkIndex = firstMonkIndex;
        this.secondMonkIndex = secondMonkIndex;
    }

    @Override
    public void run() {

        List<Integer> monks = monastery.getMonks();
        try {
            TimeUnit.MILLISECONDS.sleep((long) (Math.random()*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (monks.get(firstMonkIndex) > monks.get(secondMonkIndex)) {

            System.out.println(Thread.currentThread().getName() + " winner of the fight is " + monks.get(firstMonkIndex));
            monastery.addMonkToWinners(monks.get(firstMonkIndex));
        } else {

            System.out.println(Thread.currentThread().getName() + " winner of the fight is " + monks.get(secondMonkIndex));
            monastery.addMonkToWinners(monks.get(secondMonkIndex));
        }

    }
}