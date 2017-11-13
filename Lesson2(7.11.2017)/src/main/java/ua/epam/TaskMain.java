package ua.epam;

import ua.epam.factories.StandardVehicleFactory;
import ua.epam.factories.VehiclesFactory;
import ua.epam.utils.ConsolePrinter;
import ua.epam.vehicles.CVehicle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Создать абстрактный класс CVerhicle. На его основе реализовать классы CPlane,
 * CCar, CShip. Классы должны иметь возможность задавать и получать координаты,
 * параметры средства передвижения (цена, скорость, год выпуска). Для самолета
 * должна быть определена высота, для самолета и корабля - количество пассажиров.
 * Для корабля - порт приписки.
 *
 * Написать программу, создающую список объектов этих классов и динамической памяти.
 * Программа должна содержать меню, позволяющее осуществить проверку всех методов классов:
 *
 * 1. механизмы с наименьшей ценой с наибольшей скоростью и не старше 5 лет
 * 2. найти из механизмов Plane c с высотой полета выше 5000 с годом выпуска после 2000
 * 3. найти механизмы с максимальной скоростью в диапазоне 200 - 500, но не Plane
 * 4. добавить к данной иерархии машину-амфибию, и БетМобиль, создать 3 масива сгупированых
 * по Интерфейсам Flyable, MoveAble, SwimAble
 *
 * @author Vova Korobko
 */
public class TaskMain {
    public static final String MENU = "\n\nВыберите действие по порядковому номеру и нажмите Enter:\n" +
            "1. Вывести механизмы с наименьшей ценой, с наибольшей скоростью и не старше 5 лет\n"+
            "2. Вывести механизмы Plane c высотой полета выше 5000 и годом выпуска после 2000\n" +
            "3. Вывести механизмы со скоростью в диапазоне 200 - 500, но не Plane\n"+
            "4. Добавить к данной иерархии машину-амфибию, и БетМобиль, создать и вывести на экран " +
            "   3 массива сгупированых по Интерфейсам Flyable, MoveAble, SwimAble\n" +
            "5. Выход из программы\n";
    public static void main(String...args){
        final int listSize = 100;
        List<CVehicle> vehicleList = new ArrayList<>();

        for(int i = 0; i < listSize; i++){
            vehicleList.add(StandardVehicleFactory.createVehicle());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;

        while (true) {
            System.out.println(MENU);
            try {
                result = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("Введите цифру от 1 до 5");
            }

            switch (result) {
                case 1:
                    System.out.println("Механизмы с наибольшей скоростью");
                    System.out.println(Task1.getVehicleWithMaxSpeed(vehicleList));
                    System.out.println("Механизмы с наименьшей ценой");
                    System.out.println(Task1.getVehicleWithMinPrice(vehicleList));
                    System.out.println("Механизмы не старше 5 лет");
                    System.out.println(Task1.getVehiclesNotOlderThen5Years(vehicleList));
                    break;
                case 2:
                    System.out.println("Механизмы Plane c высотой полета выше 5000 и годом выпуска после 2000");
                    System.out.println(Task2.getPlanesByAltitudeAndYear(vehicleList));
                    break;
                case 3:
                    System.out.println("Механизмы со скоростью в диапазоне 200 - 500, но не Plane");
                    System.out.println(Task3.getSpeedInRangeNotPlane(vehicleList));
                    break;
                case 4:
                    List<CVehicle> vehicles = new ArrayList<>();

                    for(int i = 0; i < listSize; i++){
                        vehicles.add(VehiclesFactory.createVehicle());
                    }
                    System.out.println("Массив сгупированный по Интерфейсу Flyable:");
                    ConsolePrinter.printArray(Task4.getFlyable(vehicles));
                    System.out.println("Массив сгупированный по Интерфейсу Moveable:");
                    ConsolePrinter.printArray(Task4.getMovable(vehicles));
                    System.out.println("Массив сгупированный по Интерфейсу Swimable:");
                    ConsolePrinter.printArray(Task4.getSwimable(vehicles));
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Введите цифру от 1 до 5");
            }
        }
    }
}
