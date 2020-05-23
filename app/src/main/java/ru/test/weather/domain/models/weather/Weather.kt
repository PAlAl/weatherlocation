package ru.test.weather.domain.models.weather

import ru.test.weather.domain.models.weather.types.WeatherType
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat
import ru.test.weather.domain.models.weather.types.WindDirection

data class Weather(val id: Short, val type: WeatherType, val description: String,
                   val temperature: Float, val weatherUnitFormat: WeatherUnitFormat,
                   val iconPath: String, val windSpeed: Float, val windDirection: WindDirection) {
}