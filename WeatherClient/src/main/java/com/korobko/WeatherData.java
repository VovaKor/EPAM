package com.korobko;

import java.util.Observable;

/**
 *
 * @author Vova Korobko
 */

public class WeatherData extends Observable {
    private int temperature;
    private int humidity;
    private int pressure;

    public WeatherData() {
    }

    public void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(double temperature, double humidity, double pressure) {
        int newTemperature = (int) Math.round(temperature);
        int newHumidity = (int) Math.round(humidity);
        int newPressure = (int) Math.round(pressure);

        if (this.temperature != newTemperature
                || this.humidity != newHumidity
                || this.pressure != newPressure) {

            this.temperature = newTemperature;
            this.humidity = newHumidity;
            this.pressure = newPressure;
            measurementsChanged();

        }

    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }


}
