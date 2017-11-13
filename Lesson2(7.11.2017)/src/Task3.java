import vehicles.CVehicle;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 3. найти механизмы со скоростью в диапазоне 200 - 500, но не Plane
 * @author Vova Korobko
 */

public class Task3 {
    private List<CVehicle> getSpeedInRangeNotPlane(List<CVehicle> vehicles){

        return vehicles.stream()
                .filter(cVehicle -> cVehicle.getMechanismId()<1000 && cVehicle.getMechanismId()>2000)
                .filter(cVehicle -> cVehicle.getSpeed()>=200 && cVehicle.getSpeed()<=500)
                .collect(Collectors.toList());


    }
}
