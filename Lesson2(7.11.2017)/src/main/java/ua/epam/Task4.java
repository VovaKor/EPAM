package ua.epam;

import ua.epam.interfaces.FlyAble;
import ua.epam.interfaces.MoveAble;
import ua.epam.interfaces.SwimAble;
import ua.epam.vehicles.CVehicle;

import java.util.ArrayList;
import java.util.List;

/**
 * 4. добавить к данной иерархии машину-амфибию, и БетМобиль, создать 3 масива сгупированых
 * по Интерфейсам Flyable, MoveAble, SwimAble
 * @author Vova Korobko
 */
public class Task4 {

    public static FlyAble[] getFlyable(List<CVehicle> vehicles){
        ArrayList<FlyAble> flyAbles = new ArrayList<>();

        vehicles.stream()
                .filter(cVehicle -> cVehicle instanceof FlyAble)
                .forEach(cVehicle -> flyAbles.add((FlyAble) cVehicle));
        return flyAbles.toArray(new FlyAble[flyAbles.size()]);
    }

    public static MoveAble[] getMovable(List<CVehicle> vehicles){
        ArrayList<MoveAble> moveAbles = new ArrayList<>();

        vehicles.stream()
                .filter(cVehicle -> cVehicle instanceof MoveAble)
                .forEach(cVehicle -> moveAbles.add((MoveAble) cVehicle));
        return moveAbles.toArray(new MoveAble[moveAbles.size()]);
    }

    public static SwimAble[] getSwimable(List<CVehicle> vehicles){
        ArrayList<SwimAble> swimAbles = new ArrayList<>();

        vehicles.stream()
                .filter(cVehicle -> cVehicle instanceof SwimAble)
                .forEach(cVehicle -> swimAbles.add((SwimAble) cVehicle));
        return swimAbles.toArray(new SwimAble[swimAbles.size()]);
    }
}
