package ru.test.weather.domain.repositories.weather

import io.reactivex.Single
import org.joda.time.DateTime
import ru.test.weather.domain.models.Language
import ru.test.weather.domain.models.Optional
import ru.test.weather.domain.models.location.WeatherPoint
import ru.test.weather.domain.models.weather.Weather
import ru.test.weather.domain.models.weather.types.WeatherUnitFormat

interface IWeatherRepository {
    fun loadWeather(locationPoint: WeatherPoint, unitFormat: WeatherUnitFormat, language: Language): Single<Optional<Weather>>
    fun getWeatherFromCache(): Optional<Weather>
    fun getLastLoadWeatherDateTime(): Optional<DateTime>
}