package ru.test.weather.data.models.weather.mappers

import ru.test.weather.data.models.weather.response.WeatherByCoordinatesResponse
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat
import ru.test.weather.domain.models.weather.types.WindDirection

object WeatherMapper {

    fun fromResponse(model: WeatherByCoordinatesResponse, weatherUnitFormat: WeatherUnitFormat): Weather? {
        if (model.weathers.isNullOrEmpty())
            return null

        val weather = model.weathers.first()

        return Weather(weather.id, weather.type, weather.description, model.mainInfo.temperature,
                weatherUnitFormat, weather.icon, model.wind.speed, WindDirection.getWindDirectionByDegree(model.wind.degrees))
    }
}