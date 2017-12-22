package com.korobko;

import com.korobko.vehicles.CVehicle;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 1. механизмы с наименьшей ценой с наибольшей скоростью и не старше 5 лет
 * @author Vova Korobko
 */
public class Task1 {

    //Finding vehicle with lowest price
    public static CVehicle getVehicleWithMinPrice(List<CVehicle> vehicles){

        return vehicles.stream().min((o1, o2) -> {
            if(o1.getPrice()> o2.getPrice())
                return 1;
            else if(o1.getPrice()< o2.getPrice())
                return -1;
            else
                return 0;
        }).get();
    }

    //Finding vehicle with max speed
    public static CVehicle getVehicleWithMaxSpeed(List<CVehicle> vehicles){
        return vehicles.stream().max((o1, o2) -> {
            if(o1.getSpeed() > o2.getSpeed())
                return 1;
            else if(o1.getSpeed() < o2.getSpeed())
                return -1;
            else
                return 0;
        }).get();
    }

    // Finding vehicles not older then 5 years
    public static List<CVehicle> getVehiclesNotOlderThen5Years(List<CVehicle> vehicles){

        return vehicles
                .stream()
                .filter(cVehicle -> LocalDate.now().getYear() - cVehicle.getYear() <= 5)
                .collect(Collectors.toList());
    }
}
