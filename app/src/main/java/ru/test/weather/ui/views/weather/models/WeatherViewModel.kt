package ru.test.weather.ui.views.weather.models

import androidx.annotation.DrawableRes
import ru.test.weather.domain.models.weather.types.WeatherTemperatureUnit
import ru.test.weather.domain.models.weather.types.WeatherWindUnit
import ru.test.weather.domain.models.weather.types.WindDirection

data class WeatherViewModel(val temperature: String, val unit: WeatherTemperatureUnit, @DrawableRes val temperatureUnitIcon: Int,
                            val windSpeed: String, val windUnit: WeatherWindUnit, val windDirection: WindDirection,
                            val windDirectionImageRotate: Float, val imageUrl: String) {
}