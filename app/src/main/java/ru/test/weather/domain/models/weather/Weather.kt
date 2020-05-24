package ru.test.weather.domain.models.weather

import ru.test.weather.domain.models.weather.types.*

data class Weather(val id: Short, private val weatherUnitFormat: WeatherUnitFormat,
                   val type: WeatherType, val description: String, val temperature: Float,
                   val iconPath: String, val windSpeed: Float, private val degrees: Float) {

    val windDirection: WindDirection
        get() = when {
            degrees > 337.5 -> WindDirection.Northern
            degrees > 292.5 -> WindDirection.NorthWestern
            degrees > 247.5 -> WindDirection.Western
            degrees > 202.5 -> WindDirection.SouthWestern
            degrees > 157.5 -> WindDirection.Southern
            degrees > 122.5 -> WindDirection.SouthEastern
            degrees > 67.5 -> WindDirection.Eastern
            degrees > 22.5 -> WindDirection.NorthEastern
            else -> WindDirection.Northern
        }

    val temperatureUnit: WeatherTemperatureUnit
        get() = when (weatherUnitFormat) {
            WeatherUnitFormat.Standard -> WeatherTemperatureUnit.Kelvin
            WeatherUnitFormat.Metric -> WeatherTemperatureUnit.Celsius
            WeatherUnitFormat.Imperial -> WeatherTemperatureUnit.Fahrenheit
        }

    val windUnit: WeatherWindUnit
        get() = when (weatherUnitFormat) {
            WeatherUnitFormat.Standard, WeatherUnitFormat.Metric -> WeatherWindUnit.MeterBySecond
            WeatherUnitFormat.Imperial -> WeatherWindUnit.MilesByHour
        }
}