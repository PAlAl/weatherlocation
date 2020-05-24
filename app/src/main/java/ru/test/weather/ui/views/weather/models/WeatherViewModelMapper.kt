package ru.test.weather.ui.views.weather.models

import androidx.annotation.DrawableRes
import ru.test.weather.common.format
import ru.test.weather.domain.models.weather.Weather

object WeatherViewModelMapper {

    fun toViewModel(model: Weather, windDirection: Float, imageUrl: String, @DrawableRes temperatureUnitIcon: Int): WeatherViewModel {
        return WeatherViewModel(model.temperature.format(1), model.temperatureUnit, temperatureUnitIcon, model.windSpeed.format(2),
                model.windUnit, model.windDirection, windDirection, imageUrl)
    }
}