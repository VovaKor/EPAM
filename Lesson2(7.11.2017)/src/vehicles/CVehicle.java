package vehicles;

public abstract class CVehicle {
    private float longitude;
    private float latitude;
    private int price;
    private int speed;
    private int year;
    // ID is useful to get rid of instanceof operator
    private int mechanismId;

    public CVehicle(int mechanismId, int year, int price, int speed) {
        this.year = year;
        this.price = price;
        this.speed = speed;
        this.mechanismId = mechanismId;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getMechanismId() {
        return mechanismId;
    }

    public void setMechanismId(int mechanismId) {
        this.mechanismId = mechanismId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return '{'+this.getClass().getName()+
                ", price=" + price +
                ", speed=" + speed +
                ", year=" + year +
                '}';
    }
}
