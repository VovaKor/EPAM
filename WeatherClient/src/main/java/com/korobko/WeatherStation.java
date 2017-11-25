package com.korobko;

import org.openweathermap.api.UrlConnectionWeatherClient;
import org.openweathermap.api.WeatherClient;
import org.openweathermap.api.model.currentweather.CurrentWeather;
import org.openweathermap.api.query.*;
import org.openweathermap.api.query.currentweather.CurrentWeatherOneLocationQuery;

import java.util.concurrent.TimeUnit;

import static com.korobko.Constants.API_KEY;

/**
 * 1. Реализовать патерн наблюдатель(Weather Station)
 * 2. Присоеденить считываение данных по интернет для датчиков
 *
 * @author Vova Korobko
 */
public class WeatherStation {

    public static void main(String[] args) {

        CurrentWeather currentWeather;
        WeatherClient client = new UrlConnectionWeatherClient(API_KEY);

        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);

        CurrentWeatherOneLocationQuery currentWeatherOneLocationQuery = QueryBuilderPicker.pick()
                .currentWeather()                   // get current weather
                .oneLocation()                      // for one location
                .byCityName("Kiev")                 // for Kiev city
                .countryCode("UA")                  // in Ukraine
                .type(Type.ACCURATE)                // with Accurate search
                .language(Language.ENGLISH)         // in English language
                .responseFormat(ResponseFormat.JSON)// with JSON response format
                .unitFormat(UnitFormat.METRIC)      // in metric units
                .build();

        while (true){

            currentWeather = client.getCurrentWeather(currentWeatherOneLocationQuery);
            weatherData.setMeasurements(
                    currentWeather.getMainParameters().getTemperature(),
                    currentWeather.getMainParameters().getHumidity(),
                    currentWeather.getMainParameters().getPressure());
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }
}
