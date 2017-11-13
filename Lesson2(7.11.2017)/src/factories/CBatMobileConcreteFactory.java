package factories;

import vehicles.CBatMobil;

import java.util.Random;

public class CBatMobileConcreteFactory extends AbstractConcreteFactory {
    public CBatMobil create(){
        Random r = new Random();

        return new CBatMobil(r.nextInt(200) + 200,r.nextInt(49) + 1968 ,r.nextInt(10000) + 60000,
                r.nextInt(320));
    }
}
