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

    private void measurementsChanged() {
        setChanged();
        notifyObservers();
    }

    public void setMeasurements(double temperature, double humidity, double pressure) {
        int newTemperature = (int) Math.round(temperature);
        int newHumidity = (int) Math.round(humidity);
        int newPressure = (int) Math.round(pressure);

        if (isMeasurementsChanged(newTemperature, newHumidity, newPressure)) {

            this.temperature = newTemperature;
            this.humidity = newHumidity;
            this.pressure = newPressure;
            measurementsChanged();

        }

    }
    private boolean isMeasurementsChanged(int temperature, int humidity, int pressure) {
        return this.temperature != temperature
                || this.humidity != humidity
                || this.pressure != pressure;
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
