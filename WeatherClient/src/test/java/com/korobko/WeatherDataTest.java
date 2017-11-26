package com.korobko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherDataTest {

    WeatherData weatherData;
    @BeforeEach
    void setUp() {
        weatherData = new WeatherData();
    }

    @AfterEach
    void tearDown() {
        weatherData = null;
    }

    @Test
    void setMeasurements_newTemperature_accepted() {
        weatherData.setMeasurements(0.5, 0, 0);
        assertEquals(1, weatherData.getTemperature());
    }

    @Test
    void setMeasurements_newTemperature_rejected() {
        weatherData.setMeasurements(0.4, 0, 0);
        assertEquals(0, weatherData.getTemperature());
    }

    @Test
    void setMeasurements_newHumidity_accepted() {
        weatherData.setMeasurements(0, 0.5, 0);
        assertEquals(1, weatherData.getHumidity());
    }

    @Test
    void setMeasurements_newHumidity_rejected() {
        weatherData.setMeasurements(0, 0.4, 0);
        assertEquals(0, weatherData.getHumidity());
    }

    @Test
    void setMeasurements_newPressure_accepted() {
            weatherData.setMeasurements(0, 0, 0.5);
            assertEquals(1, weatherData.getPressure());
    }

    @Test
    void setMeasurements_newPressure_rejected() {
        weatherData.setMeasurements(0, 0, 0.4);
        assertEquals(0, weatherData.getPressure());
    }

}