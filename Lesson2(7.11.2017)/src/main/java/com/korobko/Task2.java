package com.korobko;

import com.korobko.vehicles.CPlane;
import com.korobko.vehicles.CVehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.korobko.utils.Constants.PLANE_MAX_ID;
import static com.korobko.utils.Constants.PLANE_MIN_ID;

/**
 * 2. найти из механизмов Plane c высотой полета выше 5000 с годом выпуска после 2000
 * @author Vova Korobko
 */

public class Task2 {

    // Method assumes Planes have unique ID's in given range
    public static List<CPlane> getPlanesByAltitudeAndYear(List<CVehicle> vehicles){
        List<CPlane> planes = new ArrayList<>();
                vehicles.stream()
                .filter(cVehicle -> cVehicle.getYear()>2000)
                .filter(cVehicle -> cVehicle.getMechanismId()> PLANE_MIN_ID && cVehicle.getMechanismId()< PLANE_MAX_ID)
                .forEach(cVehicle -> planes.add((CPlane)cVehicle));

        return planes.stream().filter(cPlane -> cPlane.getMaxAltitude()>5000).collect(Collectors.toList());
    }
}
