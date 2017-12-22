package com.korobko.factories;

import com.korobko.vehicles.CBatMobil;

import java.util.Random;

public class CBatMobileFactory extends AbstractFactory {
    public static CBatMobil create(){
        Random r = new Random();

        return new CBatMobil(r.nextInt(200) + 200,r.nextInt(49) + 1968 ,r.nextInt(10000) + 60000,
                r.nextInt(320));
    }
}
