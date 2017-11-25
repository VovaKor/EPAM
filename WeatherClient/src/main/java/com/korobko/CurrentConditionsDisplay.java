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

    public void update(Observable  obs, Object arg) {
        if (obs instanceof WeatherData) {
            WeatherData weatherData = (WeatherData)obs;
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


}
