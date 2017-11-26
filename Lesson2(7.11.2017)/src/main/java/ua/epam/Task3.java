package ua.epam;

import ua.epam.utils.Constants;
import ua.epam.vehicles.CVehicle;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 3. найти механизмы со скоростью в диапазоне 200 - 500, но не Plane
 * @author Vova Korobko
 */

public class Task3 {
    public static List<CVehicle> getSpeedInRangeNotPlane(List<CVehicle> vehicles){

        return vehicles.stream()
                .filter(cVehicle -> cVehicle.getMechanismId()< Constants.PLANE_MIN_ID && cVehicle.getMechanismId()>Constants.PLANE_MAX_ID)
                .filter(cVehicle -> cVehicle.getSpeed()>=200 && cVehicle.getSpeed()<=500)
                .collect(Collectors.toList());


    }
}
