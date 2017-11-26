package com.korobko;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Vova Korobko
 */

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private Observable observable;
    private int temperature;
    private int humidity;
    private int pressure;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable  observable, Object arg) {
        if (observable instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)observable;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: \n" + temperature
                + '\u00B0' + "C \n" + humidity + "% humidity\n"
                + pressure + " millibar pressure");
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
