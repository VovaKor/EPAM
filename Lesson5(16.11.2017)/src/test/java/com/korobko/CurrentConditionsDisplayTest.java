package com.korobko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Observable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CurrentConditionsDisplayTest {
    WeatherData weatherData;
    CurrentConditionsDisplay conditionsDisplay;

    @BeforeEach
    void setUp() {
        weatherData = mock(WeatherData.class);
        when(weatherData.getTemperature()).thenReturn(4);
        when(weatherData.getHumidity()).thenReturn(5);
        when(weatherData.getPressure()).thenReturn(6);
        conditionsDisplay = new CurrentConditionsDisplay(weatherData);
    }

    @AfterEach
    void tearDown() {
        weatherData = null;
        conditionsDisplay = null;
    }

    @Test
    void update_observableIsWeatherData_successful() {
        conditionsDisplay.update(weatherData, new Object());
        assertEquals(4, conditionsDisplay.getTemperature());
        assertEquals(5, conditionsDisplay.getHumidity());
        assertEquals(6, conditionsDisplay.getPressure());
    }

    @Test
    void update_observableIsNotWeatherData_stateNotUpdated() {
        conditionsDisplay.update(new Observable(), new Object());
        assertEquals(0, conditionsDisplay.getTemperature());
        assertEquals(0, conditionsDisplay.getHumidity());
        assertEquals(0, conditionsDisplay.getPressure());
    }

}