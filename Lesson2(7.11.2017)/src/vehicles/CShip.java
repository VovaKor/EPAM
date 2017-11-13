package vehicles;

import interfaces.SwimAble;

public class CShip extends CVehicle implements SwimAble {
    private String port;

    public CShip(int mechanismId,int year, int price,  int speed,  String port){
        super(mechanismId, year, price,  speed);
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}